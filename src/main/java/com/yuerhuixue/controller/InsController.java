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
@ResponseBody
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

        //获取当前id信息
        Ins insBefore = insService.findInsById(ins.getInsId());

        //修改图片后，删除之前的图片
        if (ins.getInsImg() != null){
            if (!insBefore.getInsImg().equals("occupancy.jpg")){
                FileManage.fileDelete(insBefore.getInsImg(),"static/uploadImg/ins");
            }
        }
        return insService.modifyIns(ins);
    }

    @ApiOperation("删除乐器接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "insId", value = "乐器id", required = true)
    })
    @DeleteMapping("/delete")
    public ResultVO deleteIns(@RequestParam("insId") Integer insId){

        //获取原图片路径
        Ins insBefore = insService.findInsById(insId);
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
    @GetMapping("/list")
    public ResultVO insList(){
        return insService.InsList();
    }
    
}
