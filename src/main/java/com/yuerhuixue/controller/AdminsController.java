package com.yuerhuixue.controller;

import com.alibaba.druid.util.StringUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yuerhuixue.pojo.Admins;
import com.yuerhuixue.pojo.Users;
import com.yuerhuixue.service.AdminsService;
import com.yuerhuixue.service.UsersService;
import com.yuerhuixue.utils.FileManage;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/admin")
@Api(value = "管理员操作接口", tags = "管理员管理")
@CrossOrigin
public class AdminsController {

    @Autowired
    private AdminsService adminsService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation("管理员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "name", value = "管理员用户名", required = true),
            @ApiImplicitParam(dataType = "string", name = "pwd", value = "管理员密码", required = true),
            @ApiImplicitParam(dataType = "string", name = "code", value = "验证码", required = true)
    })
    @GetMapping("/login")
    public ResultVO login(@RequestParam("name") String name,
                          @RequestParam("pwd") String pwd,
                          @RequestParam("code") String verificationCode){
        //判断时效
        if(!redisTemplate.hasKey("imgCode")) {
            return new ResultVO(StatusCode.NO, "验证码已过期！", null);
        }

        //判断输入，成功则进行账号密码判断
        String code = redisTemplate.opsForValue().get("imgCode").toString();
        if(StringUtils.equals(verificationCode,code)) {
            return adminsService.adminLogin(name, pwd);
        } else {
            return new ResultVO(StatusCode.NO, "验证码输入错误！", null);
        }
    }

    @ApiOperation("添加管理员接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "name", value = "管理员用户名", required = true),
            @ApiImplicitParam(dataType = "string", name = "pwd", value = "管理员密码", required = true)
    })
    @PostMapping("/regist")
    public ResultVO regist(@RequestParam("name") String name,
                           @RequestParam("pwd") String pwd){
        return adminsService.adminRegist(name, pwd);
    }

    @ApiOperation("管理员信息修改接口")
    @PutMapping("/modify")
    public ResultVO modify(@RequestBody Admins admin){
        return adminsService.adminModify(admin);
    }

    @ApiOperation("修改密码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "id", value = "管理员id", required = true),
            @ApiImplicitParam(dataType = "string", name = "pwd", value = "管理员输入密码", required = true),
            @ApiImplicitParam(dataType = "string", name = "newPwd", value = "管理员修改后密码", required = true)
    })
    @PutMapping("/modifyPwd")
    public ResultVO modifyPwd(@RequestParam("id") Integer adminId,
                              @RequestParam("pwd") String pwd,
                              @RequestParam("newPwd") String newPwd){
        return adminsService.modifyPwd(adminId,pwd,newPwd);

    }

    @ApiOperation("上传头像接口")
    @PostMapping("/uploadimg")
    public ResultVO uploadimg(@RequestParam("file") MultipartFile img) throws IOException {
        return FileManage.imgUpload(img,"static/uploadImg/head");
    }

    @ApiOperation("创建验证码接口")
    @GetMapping("/createCode")
    public void createCode(HttpServletResponse response) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 生成文字验证码
        String text = defaultKaptcha.createText();

        // 生成图片验证码
        BufferedImage image = defaultKaptcha.createImage(text);

        // 使用redis缓存验证码的值，并设置过期时间为60秒
        redisTemplate.opsForValue().set("imgCode",text,60, TimeUnit.SECONDS);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        out.flush();
        out.close();
    }

    @ApiOperation("查询用户全表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(dataType = "int", name = "pageSize", value = "当前页码数据条数", required = true)
    })
    @GetMapping("/userList")
    public ResultVO userList(Integer pageNum, Integer pageSize){
        return usersService.userList(pageNum, pageSize);
    }

    @ApiOperation("用户信息修改接口")
    @PutMapping("/userModify")
    public ResultVO userModify(@RequestBody Users user) {
        return usersService.userModifyByAdmin(user);
    }

    @ApiOperation("用户删除接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "id", value = "用户id", required = true)
    })
    @DeleteMapping("/userDelete")
    public ResultVO userDelete(@RequestParam("id") Integer userId){
        return usersService.userDelete(userId);
    }

    @ApiOperation("用户性别人数统计接口")
    @GetMapping("/userGenderTotal")
    public ResultVO userGenderTotal(){
        return usersService.userGenderTotal();
    }

    @ApiOperation("管理员数与用户数统计接口")
    @GetMapping("/userAndAdminTotal")
    public ResultVO userAndAdminTotal(){
        int adminTotal = (int) adminsService.adminTotal().getData();
        int userTotal = (int) usersService.userTotal().getData();

        HashMap<String, Integer> map = new HashMap<>();
        map.put("adminTotal", adminTotal);
        map.put("userTotal", userTotal);
        return new ResultVO(StatusCode.OK, "查询成功！", map);
    }

}
