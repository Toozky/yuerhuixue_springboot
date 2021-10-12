package com.yuerhuixue.controller;

import com.yuerhuixue.pojo.VideoType;
import com.yuerhuixue.service.VideoTypeService;
import com.yuerhuixue.utils.FileManage;
import com.yuerhuixue.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/videoType")
@Api(value = "视频类型操作接口", tags = "视频类型管理")
@CrossOrigin
public class VideoTypeController {

    @Autowired
    private VideoTypeService videoTypeService;

    @ApiOperation("添加视频类型接口")
    @PostMapping("/add")
    public ResultVO addVideoType(@RequestBody VideoType videoType){
        return videoTypeService.addVideoType(videoType);
    }

    @ApiOperation("修改视频类型接口")
    @PutMapping("/modify")
    public ResultVO modifyVideoType(@RequestBody VideoType videoType){
        return videoTypeService.modifyVideoType(videoType);
    }

    @ApiOperation("删除视频类型接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "videoTypeId", value = "视频类型id", required = true)
    })
    @DeleteMapping("/delete")
    public ResultVO deleteVideoType(@RequestParam("videoTypeId") Integer videoTypeId){

        //获取原图片路径
        VideoType videoTypeBefore = (VideoType) videoTypeService.findVideoTypeById(videoTypeId).getData();
        String imgBefore = videoTypeBefore.getTypeImg();

        //删除图片
        String targetPath = ClassUtils.getDefaultClassLoader().getResource("static/uploadImg/videoType").getPath();
        File imgBeforepath = new File(targetPath + "/" + imgBefore);
        imgBeforepath.delete();
        
        return videoTypeService.deleteVideoType(videoTypeId);
    }

    @ApiOperation("上传图片接口")
    @PostMapping("/uploadimg")
    public ResultVO uploadimg(@RequestParam("file") MultipartFile img) throws IOException {
        return FileManage.imgUpload(img,"static/uploadImg/videoType");
    }

    @ApiOperation("查询视频类型列表接口")
    @GetMapping("/list")
    public ResultVO videoTypeList(){
        return videoTypeService.VideoTypeList();
    }
    
}
