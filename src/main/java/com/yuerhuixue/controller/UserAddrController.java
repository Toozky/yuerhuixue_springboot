package com.yuerhuixue.controller;

import com.yuerhuixue.pojo.UserAddr;
import com.yuerhuixue.service.UserAddrService;
import com.yuerhuixue.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userAddr")
@Api(value = "收货地址操作接口", tags = "收货地址管理")
@CrossOrigin
public class UserAddrController {

    @Autowired
    private UserAddrService userAddrService;

    @ApiOperation("添加收货地址接口")
    @PostMapping("/add")
    public ResultVO addAddr(@RequestBody UserAddr userAddr){
        return userAddrService.addAddr(userAddr);
    }

    @ApiOperation("修改收货地址接口")
    @PutMapping("/modify")
    public ResultVO modifyAddr(@RequestBody UserAddr userAddr){
        return userAddrService.modifyAddr(userAddr);
    }

    @ApiOperation("删除收货地址接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "userAddrId", value = "收货地址id", required = true)
    })
    @DeleteMapping("/delete")
    public ResultVO deleteAddr(@RequestParam("userAddrId") Integer userAddrId){
        return userAddrService.deleteAddr(userAddrId);
    }

    @ApiOperation("收货地址列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "userId", value = "用户id", required = true)
    })
    @GetMapping("/list")
    public ResultVO list(@RequestParam("userId") Integer userId){
        return userAddrService.addrListByUserId(userId);
    }

    @ApiOperation("收货地址分页列表接口")
    @ApiImplicitParams({
        @ApiImplicitParam(dataType = "int", name = "pageNum", value = "页码", required = true),
        @ApiImplicitParam(dataType = "int", name = "pageSize", value = "当前页码数据条数", required = true),
        @ApiImplicitParam(dataType = "int", name = "userId", value = "用户id", required = true)
    })
    @GetMapping("/pageList")
    public ResultVO pageList(@RequestParam("pageNum") Integer pageNum,
                             @RequestParam("pageSize")Integer pageSize,
                             @RequestParam("userId") Integer userId){
        return userAddrService.addrPageListByUserId(pageNum, pageSize, userId);
    }

}
