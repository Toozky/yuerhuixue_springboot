package com.yuerhuixue.pojo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Ins {
    /**
     * 乐器id
     */
    @Id
    @Column(name = "ins_id")
    private Integer insId;

    /**
     * 乐器名字
     */
    @Column(name = "ins_name")
    private String insName;

    /**
     * 类型id
     */
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 乐器单价
     */
    @Column(name = "ins_price")
    private BigDecimal insPrice;

    /**
     * 乐器库存
     */
    @Column(name = "ins_stock")
    private Integer insStock;

    /**
     * 乐器图片
     */
    @Column(name = "ins_img")
    private String insImg;

    /**
     * 乐器品牌
     */
    @Column(name = "ins_brand")
    private String insBrand;

    /**
     * 乐器简介
     */
    @Column(name = "ins_desc")
    private String insDesc;

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
     * 获取乐器id
     *
     * @return ins_id - 乐器id
     */
    public Integer getInsId() {
        return insId;
    }

    /**
     * 设置乐器id
     *
     * @param insId 乐器id
     */
    public void setInsId(Integer insId) {
        this.insId = insId;
    }

    /**
     * 获取乐器名字
     *
     * @return ins_name - 乐器名字
     */
    public String getInsName() {
        return insName;
    }

    /**
     * 设置乐器名字
     *
     * @param insName 乐器名字
     */
    public void setInsName(String insName) {
        this.insName = insName;
    }

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
     * 获取乐器单价
     *
     * @return ins_price - 乐器单价
     */
    public BigDecimal getInsPrice() {
        return insPrice;
    }

    /**
     * 设置乐器单价
     *
     * @param insPrice 乐器单价
     */
    public void setInsPrice(BigDecimal insPrice) {
        this.insPrice = insPrice;
    }

    /**
     * 获取乐器库存
     *
     * @return ins_stock - 乐器库存
     */
    public Integer getInsStock() {
        return insStock;
    }

    /**
     * 设置乐器库存
     *
     * @param insStock 乐器库存
     */
    public void setInsStock(Integer insStock) {
        this.insStock = insStock;
    }

    /**
     * 获取乐器图片
     *
     * @return ins_img - 乐器图片
     */
    public String getInsImg() {
        return insImg;
    }

    /**
     * 设置乐器图片
     *
     * @param insImg 乐器图片
     */
    public void setInsImg(String insImg) {
        this.insImg = insImg;
    }

    /**
     * 获取乐器品牌
     *
     * @return ins_brand - 乐器品牌
     */
    public String getInsBrand() {
        return insBrand;
    }

    /**
     * 设置乐器品牌
     *
     * @param insBrand 乐器品牌
     */
    public void setInsBrand(String insBrand) {
        this.insBrand = insBrand;
    }

    /**
     * 获取乐器简介
     *
     * @return ins_desc - 乐器简介
     */
    public String getInsDesc() {
        return insDesc;
    }

    /**
     * 设置乐器简介
     *
     * @param insDesc 乐器简介
     */
    public void setInsDesc(String insDesc) {
        this.insDesc = insDesc;
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