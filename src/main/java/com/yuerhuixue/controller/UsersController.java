package com.yuerhuixue.controller;

import com.yuerhuixue.pojo.Users;
import com.yuerhuixue.service.UsersService;
import com.yuerhuixue.utils.FileManage;
import com.yuerhuixue.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@Api(value = "用户操作接口", tags = "用户管理")
@ResponseBody
@CrossOrigin
public class UsersController {

    @Autowired
    private UsersService usersService;

    @ApiOperation("用户登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "name", value = "用户名", required = true),
            @ApiImplicitParam(dataType = "string", name = "pwd", value = "用户密码", required = true)
    })
    @GetMapping("/login")
    public ResultVO login(@RequestParam("name") String name,
                          @RequestParam("pwd") String pwd){
        return usersService.userLogin(name, pwd);
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
        Users userBefore = usersService.findUserById(user.getUserId());

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

}
