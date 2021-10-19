package com.yuerhuixue.controller;

import com.yuerhuixue.pojo.Ins;
import com.yuerhuixue.service.InsService;
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
@RequestMapping("/ins")
@Api(value = "乐器操作接口", tags = "乐器管理")
@CrossOrigin
public class InsController {
    
    @Autowired
    private InsService insService;

    @ApiOperation("添加乐器接口")
    @PostMapping("/add")
    public ResultVO addIns(@RequestBody Ins ins){
        return insService.addIns(ins);
    }

    @ApiOperation("修改乐器接口")
    @PutMapping("/modify")
    public ResultVO modifyIns(@RequestBody Ins ins){
        return insService.modifyIns(ins);
    }

    @ApiOperation("删除乐器接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "insId", value = "乐器id", required = true)
    })
    @DeleteMapping("/delete")
    public ResultVO deleteIns(@RequestParam("insId") Integer insId){

        //获取原图片路径
        Ins insBefore = (Ins) insService.findInsById(insId).getData();
        String imgBefore = insBefore.getInsImg();

        //删除图片
        FileManage.fileDelete(imgBefore, "static/uploadImg/ins");

        return insService.deleteIns(insId);
    }

    @ApiOperation("上传图片接口")
    @PostMapping("/uploadimg")
    public ResultVO uploadimg(@RequestParam("file") MultipartFile img) throws IOException {
        return FileManage.imgUpload(img,"static/uploadImg/ins");
    }

    @ApiOperation("查询乐器列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(dataType = "int", name = "pageSize", value = "当前页码数据条数", required = true)
    })
    @GetMapping("/pageList")
    public ResultVO insList(@RequestParam("pageNum") Integer pageNum,
                            @RequestParam("pageSize")Integer pageSize){
        return insService.insList(pageNum, pageSize);
    }

    @ApiOperation("查询前五条乐器列表接口")
    @GetMapping("/fiveList")
    public ResultVO insListFive(){
        return insService.insListFive();
    }

    @ApiOperation("查询选中乐器信息接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "insId", value = "乐器id", required = true)
    })
    @GetMapping("/insInfo")
    public ResultVO insInfo(@RequestParam("insId") Integer insId){
        return insService.findInsVOById(insId);
    }

    @ApiOperation("查询最贵的5个乐器接口")
    @GetMapping("/mostFiveExpensive")
    public ResultVO mostFiveExpensive(){
        return insService.mostFiveExpensive();
    }
    
}
