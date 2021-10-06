package com.yuerhuixue.service.impl;

import com.yuerhuixue.dao.VideoMapper;
import com.yuerhuixue.dao.VideoTypeMapper;
import com.yuerhuixue.pojo.Video;
import com.yuerhuixue.pojo.VideoType;
import com.yuerhuixue.service.VideoTypeService;
import com.yuerhuixue.vo.ResultVO;
import com.yuerhuixue.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class VideoTypeServiceImpl implements VideoTypeService {

    @Autowired
    private VideoTypeMapper videoTypeMapper;

    @Autowired
    private VideoMapper videoMapper;

    /**
     * 添加视频类型信息
     * @param videoType 视频类型信息
     * @return 执行结果
     */
    @Override
    public ResultVO addVideoType(VideoType videoType) {

        //设置创建和更新时间
        videoType.setCreateTime(new Date());
        videoType.setUpdateTime(new Date());

        //如果图片为空，则默认
        if (videoType.getTypeImg() == null){
            videoType.setTypeImg("occupancy.jpg");
        }

        //是否添加成功
        int i = videoTypeMapper.insertUseGeneratedKeys(videoType);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "添加成功！", videoType);
        }else {
            return new ResultVO(StatusCode.NO, "添加失败！", null);
        }
    }

    /**
     * 修改视频类型信息
     * @param videoType 视频类型信息
     * @return 执行结果
     */
    @Override
    public ResultVO modifyVideoType(VideoType videoType) {

        //更新修改时间
        videoType.setUpdateTime(new Date());

        //是否修改成功
        int i = videoTypeMapper.updateByPrimaryKeySelective(videoType);
        if (i > 0){
            return new ResultVO(StatusCode.OK, "修改成功！", videoType);
        }else {
            return new ResultVO(StatusCode.NO, "修改失败！", null);
        }
    }

    /**
     * 删除视频类型信息
     * @param videoTypeId 视频id
     * @return 执行结果
     */
    @Override
    public ResultVO deleteVideoType(Integer videoTypeId) {

        //是否删除成功
        int i = videoTypeMapper.deleteByPrimaryKey(videoTypeId);
        if (i > 0) {
            return new ResultVO(StatusCode.OK, "删除成功！", null);
        }else {
            return new ResultVO(StatusCode.OK, "删除失败！", null);
        }
    }

    /**
     * 查询所有视频
     * @return 执行结果
     */
    @Override
    public ResultVO VideoTypeList() {

        //查询所有
        List<VideoType> videoTypeList = videoTypeMapper.selectAll();
        return new ResultVO(StatusCode.OK, "查询完成！", videoTypeList);
    }

    /**
     * 根据id查询视频类型
     * @param videoTypeId 视频类型id
     * @return 视频类型对象
     */
    @Override
    public ResultVO findVideoTypeById(Integer videoTypeId) {
        VideoType videoType = videoTypeMapper.selectByPrimaryKey(videoTypeId);
        return new ResultVO(StatusCode.OK, "查询完成！", videoType);
    }

    /**
     * 根据id查询当前类型视频
     * @param videoTypeId 视频类型id
     * @return 执行结果
     */
    @Override
    public ResultVO videoByType(Integer videoTypeId) {

        //根据id查询当前类型视频
        Example example = new Example(Video.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeId", videoTypeId);
        List<Video> videoList = videoMapper.selectByExample(example);

        return new ResultVO(StatusCode.OK, "查询完成！", videoList);

    }
}
