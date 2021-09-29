package com.yuerhuixue.pojo;

import java.util.Date;
import javax.persistence.*;

public class Video {
    /**
     * 视频id
     */
    @Id
    @Column(name = "video_id")
    private Integer videoId;

    /**
     * 视频标题
     */
    @Column(name = "video_title")
    private String videoTitle;

    /**
     * 视频封面
     */
    @Column(name = "video_img")
    private String videoImg;

    /**
     * 视频简介
     */
    @Column(name = "video_desc")
    private String videoDesc;

    /**
     * 视频类型
     */
    @Column(name = "type_id")
    private String typeId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 视频路径
     */
    @Column(name = "video_url")
    private String videoUrl;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 获取视频id
     *
     * @return video_id - 视频id
     */
    public Integer getVideoId() {
        return videoId;
    }

    /**
     * 设置视频id
     *
     * @param videoId 视频id
     */
    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    /**
     * 获取视频标题
     *
     * @return video_title - 视频标题
     */
    public String getVideoTitle() {
        return videoTitle;
    }

    /**
     * 设置视频标题
     *
     * @param videoTitle 视频标题
     */
    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    /**
     * 获取视频封面
     *
     * @return video_img - 视频封面
     */
    public String getVideoImg() {
        return videoImg;
    }

    /**
     * 设置视频封面
     *
     * @param videoImg 视频封面
     */
    public void setVideoImg(String videoImg) {
        this.videoImg = videoImg;
    }

    /**
     * 获取视频简介
     *
     * @return video_desc - 视频简介
     */
    public String getVideoDesc() {
        return videoDesc;
    }

    /**
     * 设置视频简介
     *
     * @param videoDesc 视频简介
     */
    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    /**
     * 获取视频类型
     *
     * @return type_id - 视频类型
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置视频类型
     *
     * @param typeId 视频类型
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取视频路径
     *
     * @return video_url - 视频路径
     */
    public String getVideoUrl() {
        return videoUrl;
    }

    /**
     * 设置视频路径
     *
     * @param videoUrl 视频路径
     */
    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    /**
     * 获取添加时间
     *
     * @return create_time - 添加时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置添加时间
     *
     * @param createTime 添加时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取修改时间
     *
     * @return update_time - 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置修改时间
     *
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}