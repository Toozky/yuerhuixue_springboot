package com.yuerhuixue.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "video_type")
public class VideoType {
    /**
     * 类型id
     */
    @Id
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 类型名字
     */
    @Column(name = "type_name")
    private String typeName;

    /**
     * 类型图片
     */
    @Column(name = "type_img")
    private String typeImg;

    /**
     * 类型简介
     */
    @Column(name = "type_desc")
    private String typeDesc;

    /**
     * 类型级别
     */
    @Column(name = "type_level")
    private String typeLevel;

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
     * 获取类型id
     *
     * @return type_id - 类型id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 设置类型id
     *
     * @param typeId 类型id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取类型名字
     *
     * @return type_name - 类型名字
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置类型名字
     *
     * @param typeName 类型名字
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取类型图片
     *
     * @return type_img - 类型图片
     */
    public String getTypeImg() {
        return typeImg;
    }

    /**
     * 设置类型图片
     *
     * @param typeImg 类型图片
     */
    public void setTypeImg(String typeImg) {
        this.typeImg = typeImg;
    }

    /**
     * 获取类型简介
     *
     * @return type_desc - 类型简介
     */
    public String getTypeDesc() {
        return typeDesc;
    }

    /**
     * 设置类型简介
     *
     * @param typeDesc 类型简介
     */
    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    /**
     * 获取类型级别
     *
     * @return type_level - 类型级别
     */
    public String getTypeLevel() {
        return typeLevel;
    }

    /**
     * 设置类型级别
     *
     * @param typeLevel 类型级别
     */
    public void setTypeLevel(String typeLevel) {
        this.typeLevel = typeLevel;
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