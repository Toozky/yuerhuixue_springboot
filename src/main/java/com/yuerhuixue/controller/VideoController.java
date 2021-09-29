package com.yuerhuixue.controller;

import com.yuerhuixue.pojo.Video;
import com.yuerhuixue.service.VideoService;
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
@RequestMapping("/video")
@Api(value = "视频操作接口", tags = "视频管理")
@ResponseBody
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("添加视频接口")
    @PostMapping("/add")
    public ResultVO addVideo(@RequestBody Video video){
        return videoService.addVideo(video);
    }

    @ApiOperation("修改视频接口")
    @PutMapping("/modify")
    public ResultVO modifyVideo(@RequestBody Video video){

        //获取当前id信息
        Video videoBefore = videoService.findVideoById(video.getVideoId());

        //修改图片后，删除之前的图片
        if (video.getVideoImg() != null){
            if (!videoBefore.getVideoImg().equals("occupancy.jpg")){
                FileManage.fileDelete(videoBefore.getVideoImg(),"static/uploadImg/video");
            }
        }

        //修改视频后，删除之前的视频
        if (video.getVideoUrl() != null){
            FileManage.fileDelete(videoBefore.getVideoUrl(), "static/uploadVideo");
        }

        return videoService.modifyVideo(video);
    }

    @ApiOperation("删除视频接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "integer", name = "videoId", value = "视频id", required = true)
    })
    @DeleteMapping("/delete")
    public ResultVO deleteVideo(@RequestParam("videoId") Integer videoId){

        //获取原图片、视频路径
        Video videoBefore = videoService.findVideoById(videoId);
        String imgBefore = videoBefore.getVideoImg();
        String movieBefore = videoBefore.getVideoUrl();

        //删除图片、视频
        FileManage.fileDelete(imgBefore, "static/uploadImg/video");
        FileManage.fileDelete(movieBefore, "static/uploadVideo");
        
        return videoService.deleteVideo(videoId);
    }

    @ApiOperation("上传图片接口")
    @PostMapping("/uploadimg")
    public ResultVO uploadimg(@RequestParam("file") MultipartFile img) throws IOException {
        return FileManage.imgUpload(img,"static/uploadImg/video");
    }

    @ApiOperation("上传视频接口")
    @PostMapping("/uploadvideo")
    public ResultVO uploadvideo(@RequestParam("file") MultipartFile video) throws IOException {
        return FileManage.videoUpload(video,"static/uploadVideo");
    }

    @ApiOperation("查询视频列表接口")
    @GetMapping("/list")
    public ResultVO videoList(){
        return videoService.VideoList();
    }
    
}
