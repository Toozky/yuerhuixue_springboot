package com.yuerhuixue.service;

import com.yuerhuixue.pojo.Video;
import com.yuerhuixue.pojo.VideoVO;
import com.yuerhuixue.vo.ResultVO;

public interface VideoService {

    //增加视频
    ResultVO addVideo(Video video);

    //修改视频
    ResultVO modifyVideo(Video video);

    //删除视频
    ResultVO deleteVideo(Integer videoId);

    //查询所有视频
    ResultVO VideoList(Integer pageNum, Integer pageSize);

    //根据id查询视频
    ResultVO findVideoById(Integer videoId);

    //根据id查询视频
    ResultVO findVideoVOById(Integer videoId);
    
}
