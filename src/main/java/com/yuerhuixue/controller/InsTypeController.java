package com.yuerhuixue.controller;

import com.yuerhuixue.pojo.InsType;
import com.yuerhuixue.service.InsTypeService;
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
@RequestMapping("/insType")
@Api(value = "乐器类型操作接口", tags = "乐器类型管理")
@CrossOrigin
public class InsTypeController {

    @Autowired
    private InsTypeService insTypeService;

    @ApiOperation("添加乐器类型接口")
    @PostMapping("/add")
    public ResultVO addInsType(@RequestBody InsType insType){
        return insTypeService.addInsType(insType);
    }

    @ApiOperation("修改乐器类型接口")
    @PutMapping("/modify")
    public ResultVO modifyInsType(@RequestBody InsType insType){
        return insTypeService.modifyInsType(insType);
    }

    @ApiOperation("删除乐器类型接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "insTypeId", value = "乐器类型id", required = true)
    })
    @DeleteMapping("/delete")
    public ResultVO deleteInsType(@RequestParam("insTypeId") Integer insTypeId){

        //获取原图片路径
        InsType insTypeBefore = (InsType) insTypeService.findInsTypeById(insTypeId).getData();
        String imgBefore = insTypeBefore.getTypeImg();

        //删除图片
        FileManage.fileDelete(imgBefore, "static/uploadImg/insType");

        return insTypeService.deleteInsType(insTypeId);
    }

    @ApiOperation("上传图片接口")
    @PostMapping("/uploadimg")
    public ResultVO uploadimg(@RequestParam("file") MultipartFile img) throws IOException {
        return FileManage.imgUpload(img,"static/uploadImg/insType");
    }

    @ApiOperation("查询乐器类型列表接口")
    @GetMapping("/list")
    public ResultVO insTypeList(){
        return insTypeService.insTypeList();
    }

    @ApiOperation("查询前五条乐器列表接口")
    @GetMapping("/fiveList")
    public ResultVO insTypeListFive(){
        return insTypeService.insTypeListFive();
    }

    @ApiOperation("查询当前类型下所有乐器接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "insTypeId", value = "乐器类型id", required = true),
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(dataType = "int", name = "pageSize", value = "当前页码数据条数", required = true)
    })
    @GetMapping("/insByType")
    public ResultVO insByType(@RequestParam("insTypeId")Integer insTypeId,
                              @RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize")Integer pageSize){
        return insTypeService.insByType(insTypeId, pageNum, pageSize);
    }

    @ApiOperation("查询乐器类型列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "页码", required = true),
            @ApiImplicitParam(dataType = "int", name = "pageSize", value = "当前页码数据条数", required = true)
    })
    @GetMapping("/pageList")
    public ResultVO insTypeList(Integer pageNum, Integer pageSize){
        return insTypeService.insTypePageList(pageNum, pageSize);
    }

    @ApiOperation("统计各个类型乐器的个数接口")
    @GetMapping("/insTotalByType")
    public ResultVO insTotalByType(){
        return insTypeService.insTotalByType();
    }

}
