package com.yuerhuixue.pojo;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

public class Orders {
    /**
     * 订单id
     */
    @Id
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 所有产品
     */
    @Column(name = "all_goods")
    private String allGoods;

    /**
     * 收货人名字（快照）
     */
    @Column(name = "receiver_name")
    private String receiverName;

    /**
     * 收货人电话（快照）
     */
    @Column(name = "receiver_tel")
    private String receiverTel;

    /**
     * 收货地址（快照）
     */
    @Column(name = "receiver_addr")
    private String receiverAddr;

    /**
     * 总价
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 实付
     */
    @Column(name = "actual_amount")
    private BigDecimal actualAmount;

    /**
     * 支付方式 1:支付宝 2.微信 3.银行卡
     */
    @Column(name = "pay_type")
    private String payType;

    /**
     * 订单备注
     */
    @Column(name = "order_remark")
    private String orderRemark;

    /**
     * 订单状态 1:待付款 2:待发货 3:待收货 4:已完成
     */
    private String status;

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
     * 支付时间
     */
    @Column(name = "pay_time")
    private Date payTime;

    /**
     * 发货时间
     */
    @Column(name = "deliver_time")
    private Date deliverTime;

    /**
     * 收货时间
     */
    @Column(name = "receipt_time")
    private Date receiptTime;

    /**
     * 完成时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 获取订单id
     *
     * @return order_id - 订单id
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
     * 获取所有产品
     *
     * @return all_goods - 所有产品
     */
    public String getAllGoods() {
        return allGoods;
    }

    /**
     * 设置所有产品
     *
     * @param allGoods 所有产品
     */
    public void setAllGoods(String allGoods) {
        this.allGoods = allGoods;
    }

    /**
     * 获取收货人名字（快照）
     *
     * @return receiver_name - 收货人名字（快照）
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 设置收货人名字（快照）
     *
     * @param receiverName 收货人名字（快照）
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    /**
     * 获取收货人电话（快照）
     *
     * @return receiver_tel - 收货人电话（快照）
     */
    public String getReceiverTel() {
        return receiverTel;
    }

    /**
     * 设置收货人电话（快照）
     *
     * @param receiverTel 收货人电话（快照）
     */
    public void setReceiverTel(String receiverTel) {
        this.receiverTel = receiverTel;
    }

    /**
     * 获取收货地址（快照）
     *
     * @return receiver_addr - 收货地址（快照）
     */
    public String getReceiverAddr() {
        return receiverAddr;
    }

    /**
     * 设置收货地址（快照）
     *
     * @param receiverAddr 收货地址（快照）
     */
    public void setReceiverAddr(String receiverAddr) {
        this.receiverAddr = receiverAddr;
    }

    /**
     * 获取总价
     *
     * @return total_amount - 总价
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设置总价
     *
     * @param totalAmount 总价
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 获取实付
     *
     * @return actual_amount - 实付
     */
    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    /**
     * 设置实付
     *
     * @param actualAmount 实付
     */
    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    /**
     * 获取支付方式 1:支付宝 2.微信 3.银行卡
     *
     * @return pay_type - 支付方式 1:支付宝 2.微信 3.银行卡
     */
    public String getPayType() {
        return payType;
    }

    /**
     * 设置支付方式 1:支付宝 2.微信 3.银行卡
     *
     * @param payType 支付方式 1:支付宝 2.微信 3.银行卡
     */
    public void setPayType(String payType) {
        this.payType = payType;
    }

    /**
     * 获取订单备注
     *
     * @return order_remark - 订单备注
     */
    public String getOrderRemark() {
        return orderRemark;
    }

    /**
     * 设置订单备注
     *
     * @param orderRemark 订单备注
     */
    public void setOrderRemark(String orderRemark) {
        this.orderRemark = orderRemark;
    }

    /**
     * 获取订单状态 1:待付款 2:待发货 3:待收货 4:已完成
     *
     * @return status - 订单状态 1:待付款 2:待发货 3:待收货 4:已完成
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置订单状态 1:待付款 2:待发货 3:待收货 4:已完成
     *
     * @param status 订单状态 1:待付款 2:待发货 3:待收货 4:已完成
     */
    public void setStatus(String status) {
        this.status = status;
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

    /**
     * 获取支付时间
     *
     * @return pay_time - 支付时间
     */
    public Date getPayTime() {
        return payTime;
    }

    /**
     * 设置支付时间
     *
     * @param payTime 支付时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取发货时间
     *
     * @return deliver_time - 发货时间
     */
    public Date getDeliverTime() {
        return deliverTime;
    }

    /**
     * 设置发货时间
     *
     * @param deliverTime 发货时间
     */
    public void setDeliverTime(Date deliverTime) {
        this.deliverTime = deliverTime;
    }

    /**
     * 获取收货时间
     *
     * @return receipt_time - 收货时间
     */
    public Date getReceiptTime() {
        return receiptTime;
    }

    /**
     * 设置收货时间
     *
     * @param receiptTime 收货时间
     */
    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

    /**
     * 获取完成时间
     *
     * @return finish_time - 完成时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置完成时间
     *
     * @param finishTime 完成时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}