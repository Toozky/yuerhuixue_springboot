package com.yuerhuixue.controller;

import com.alibaba.druid.util.StringUtils;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yuerhuixue.pojo.Users;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Api(value = "用户操作接口", tags = "用户管理")
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "name", value = "用户名", required = true),
            @ApiImplicitParam(dataType = "string", name = "pwd", value = "用户密码", required = true),
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
            return usersService.userLogin(name, pwd);
        } else {
            return new ResultVO(StatusCode.NO, "验证码输入错误！", null);
        }
    }

    @ApiOperation("用户注册接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "name", value = "用户名", required = true),
            @ApiImplicitParam(dataType = "string", name = "pwd", value = "用户密码", required = true)
    })
    @PostMapping("/regist")
    public ResultVO regist(@RequestParam("name") String name,
                          @RequestParam("pwd") String pwd){
        return usersService.userRegist(name, pwd);
    }

    @ApiOperation("用户信息修改接口")
    @PutMapping("/modify")
    public ResultVO modify(@RequestBody Users user) {

        //获取当前id信息
        Users userBefore = (Users) usersService.findUserById(user.getUserId()).getData();

        //修改图片后，删除之前的图片
        if (user.getUserImg() != null){
            if (!userBefore.getUserImg().equals("headDefault.jpg")){
                FileManage.fileDelete(userBefore.getUserImg(),"static/uploadImg/head");
            }
        }
        return usersService.userModify(user);
    }

    @ApiOperation("修改密码接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "id", value = "用户id", required = true),
            @ApiImplicitParam(dataType = "string", name = "pwd", value = "用户输入密码", required = true),
            @ApiImplicitParam(dataType = "string", name = "newPwd", value = "用户修改后密码", required = true)
    })
    @PutMapping("/modifyPwd")
    public ResultVO modifyPwd(@RequestParam("id") Integer userId,
                              @RequestParam("pwd") String pwd,
                              @RequestParam("newPwd") String newPwd){
        return usersService.modifyPwd(userId,pwd,newPwd);

    }

    @ApiOperation("上传头像接口")
    @PostMapping("/uploadimg")
    public ResultVO uploadimg(@RequestParam("file") MultipartFile img) throws IOException {
        return FileManage.imgUpload(img,"static/uploadImg/head");
    }

    /**
     * 创建验证码
     * @param response 服务器响应
     * @throws IOException 异常
     */
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
}
