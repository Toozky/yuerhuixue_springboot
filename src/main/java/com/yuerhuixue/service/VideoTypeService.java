package com.yuerhuixue.service;

import com.yuerhuixue.pojo.VideoType;
import com.yuerhuixue.vo.ResultVO;

public interface VideoTypeService {

    //增加视频类型
    ResultVO addVideoType(VideoType videoType);

    //修改视频类型
    ResultVO modifyVideoType(VideoType videoType);

    //删除视频类型
    ResultVO deleteVideoType(Integer videoTypeId);

    //查询所有视频类型
    ResultVO VideoTypeList();

    //根据id查询视频类型
    VideoType findVideoTypeById(Integer videoTypeId);

    //查询当前类型下所有视频
    ResultVO videoByType(Integer videoTypeId);
    
}
