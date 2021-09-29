package com.yuerhuixue.dao;

import com.yuerhuixue.general.GeneralDao;
import com.yuerhuixue.pojo.Video;
import com.yuerhuixue.pojo.VideoVO;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoMapper extends GeneralDao<Video> {

    //根据id查询视频
    VideoVO findVideoVOById(Integer videoId);

}