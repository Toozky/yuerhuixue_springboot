package com.yuerhuixue.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_addr")
public class UserAddr {
    /**
     * 地址id
     */
    @Id
    @Column(name = "addr_id")
    private Integer addrId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 收货人名字
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 收货人电话
     */
    @Column(name = "receiver_tel")
    private String receiverTel;

    /**
     * 收货地址
     */
    @Column(name = "receiver_addr")
    private String receiverAddr;

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
     * 获取地址id
     *
     * @return addr_id - 地址id
     */
    public Integer getAddrId() {
        return addrId;
    }

    /**
     * 设置地址id
     *
     * @param addrId 地址id
     */
    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
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
     * 获取收货人名字
     *
     * @return receiver_name - 收货人名字
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 设置收货人名字
     *
     * @param receiverName 收货人名字
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * 获取收货人电话
     *
     * @return receiver_tel - 收货人电话
     */
    public String getReceiverTel() {
        return receiverTel;
    }

    /**
     * 设置收货人电话
     *
     * @param receiverTel 收货人电话
     */
    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    /**
     * 获取收货地址
     *
     * @return receiver_addr - 收货地址
     */
    public String getReceiverAddr() {
        return receiverAddr;
    }

    /**
     * 设置收货地址
     *
     * @param receiverAddr 收货地址
     */
    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
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