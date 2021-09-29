package com.yuerhuixue.controller;

import com.yuerhuixue.pojo.Admins;
import com.yuerhuixue.service.AdminsService;
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
@RequestMapping("/admin")
@Api(value = "管理员操作接口", tags = "管理员管理")
@ResponseBody
@CrossOrigin
public class AdminsController {

    @Autowired
    private AdminsService adminsService;

    @ApiOperation("管理员登录接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string", name = "name", value = "管理员用户名", required = true),
            @ApiImplicitParam(dataType = "string", name = "pwd", value = "管理员密码", required = true)
    })
    @GetMapping("/login")
    public ResultVO login(@RequestParam("name") String name,
                          @RequestParam("pwd") String pwd){
        return adminsService.adminLogin(name, pwd);
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
        
        //获取当前id信息
        Admins adminBefore = adminsService.findAdminById(admin.getAdminId());

        //修改图片后，删除之前的图片
        if (admin.getAdminImg() != null){
            if (!adminBefore.getAdminImg().equals("headDefault.jpg")){
                FileManage.fileDelete(adminBefore.getAdminImg(),"static/uploadImg/head");
            }
        }
        return adminsService.adminModify(admin);
    }

    @ApiOperation("上传头像接口")
    @PostMapping("/uploadimg")
    public ResultVO uploadimg(@RequestParam("file") MultipartFile img) throws IOException {
        return FileManage.imgUpload(img,"static/uploadImg/head");
    }

}
