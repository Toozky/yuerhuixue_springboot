package com.yuerhuixue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yuerhuixue.dao.VideoMapper;
import com.yuerhuixue.pojo.Video;
import com.yuerhuixue.pojo.VideoVO;
import com.yuerhuixue.service.VideoService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 添加视频信息
     * @param video 视频信息
     * @return 执行结果
     */
    @Override
    public ResultVO addVideo(Video video) {

        //设置创建和更新时间
        video.setCreateTime(new Date());
        video.setUpdateTime(new Date());

        //如果图片为空，则默认
        if (video.getVideoImg() == null){
            video.setVideoImg("occupancy.jpg");
        }

        //如果视频为空，则返回添加失败
        if (video.getVideoUrl() == null){
            return new ResultVO(StatusCode.NO, "请上传视频！", null);
        }

        //是否添加成功
        int i = videoMapper.insertUseGeneratedKeys(video);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "添加成功！", video);
        }else {
            return new ResultVO(StatusCode.NO, "添加失败！", null);
        }
    }

    /**
     * 修改视频信息
     * @param video 视频信息
     * @return 执行结果
     */
    @Override
    public ResultVO modifyVideo(Video video) {

        //更新修改时间
        video.setCreateTime(null);
        video.setUpdateTime(new Date());

        //是否修改成功
        int i = videoMapper.updateByPrimaryKeySelective(video);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "修改成功！", video);
        }else {
            return new ResultVO(StatusCode.NO, "修改失败！", null);
        }
    }

    /**
     * 删除视频信息
     * @param videoId 视频id
     * @return 执行结果
     */
    @Override
    public ResultVO deleteVideo(Integer videoId) {

        //是否删除成功
        int i = videoMapper.deleteByPrimaryKey(videoId);
        if (i > 0) {
            return new ResultVO(StatusCode.OK, "删除成功！", null);
        }else {
            return new ResultVO(StatusCode.OK, "删除失败！", null);
        }
    }


    /**
     * 查询视频列表
     * @param pageNum 页码
     * @param pageSize 当前页码数据条数
     * @return 执行结果
     */
    @Override
    public ResultVO VideoList(Integer pageNum, Integer pageSize) {

        //分页
        PageHelper.startPage(pageNum, pageSize);

        //查询
        List<Video> videoList = videoMapper.selectAll();
        PageInfo<Video> videoPageInfo = new PageInfo<>(videoList);
        return new ResultVO(StatusCode.OK, "查询完成！", videoPageInfo);
    }

    /**
     * 根据id查询视频
     * @param videoId 视频id
     * @return 视频对象
     */
    @Override
    public ResultVO findVideoById(Integer videoId) {
        Video video = videoMapper.selectByPrimaryKey(videoId);
        return new ResultVO(StatusCode.OK, "查询完成！", video);
    }

    /**
     * 根据id查询视频
     * @param videoId 视频id
     * @return 视频对象
     */
    @Override
    public ResultVO findVideoVOById(Integer videoId) {
        VideoVO videoVO = videoMapper.findVideoVOById(videoId);
        return new ResultVO(StatusCode.OK, "查询完成！", videoVO);
    }
}
