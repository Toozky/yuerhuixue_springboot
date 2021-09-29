package com.yuerhuixue.pojo;

import java.math.BigDecimal;
import javax.persistence.*;

@Table(name = "order_detail")
public class OrderDetail {
    /**
     * 明细id
     */
    @Id
    @Column(name = "detail_id")
    private Integer detailId;

    /**
     * 订单id
     */
    @Column(name = "order_id")
    private Integer orderId;

    /**
     * 乐器id
     */
    @Column(name = "ins_id")
    private String insId;

    /**
     * 乐器名（快照）
     */
    @Column(name = "ins_name")
    private String insName;

    /**
     * 乐器图片（快照）
     */
    @Column(name = "ins_img")
    private String insImg;

    /**
     * 乐器单价（快照）
     */
    @Column(name = "ins_price")
    private BigDecimal insPrice;

    /**
     * 购买数量
     */
    @Column(name = "buy_number")
    private Integer buyNumber;

    /**
     * 总价
     */
    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 获取明细id
     *
     * @return detail_id - 明细id
     */
    public Integer getDetailId() {
        return detailId;
    }

    /**
     * 设置明细id
     *
     * @param detailId 明细id
     */
    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

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
     * 获取乐器id
     *
     * @return ins_id - 乐器id
     */
    public String getInsId() {
        return insId;
    }

    /**
     * 设置乐器id
     *
     * @param insId 乐器id
     */
    public void setInsId(String insId) {
        this.insId = insId;
    }

    /**
     * 获取乐器名（快照）
     *
     * @return ins_name - 乐器名（快照）
     */
    public String getInsName() {
        return insName;
    }

    /**
     * 设置乐器名（快照）
     *
     * @param insName 乐器名（快照）
     */
    public void setInsName(String insName) {
        this.insName = insName;
    }

    /**
     * 获取乐器图片（快照）
     *
     * @return ins_img - 乐器图片（快照）
     */
    public String getInsImg() {
        return insImg;
    }

    /**
     * 设置乐器图片（快照）
     *
     * @param insImg 乐器图片（快照）
     */
    public void setInsImg(String insImg) {
        this.insImg = insImg;
    }

    /**
     * 获取乐器单价（快照）
     *
     * @return ins_price - 乐器单价（快照）
     */
    public BigDecimal getInsPrice() {
        return insPrice;
    }

    /**
     * 设置乐器单价（快照）
     *
     * @param insPrice 乐器单价（快照）
     */
    public void setInsPrice(BigDecimal insPrice) {
        this.insPrice = insPrice;
    }

    /**
     * 获取购买数量
     *
     * @return buy_number - 购买数量
     */
    public Integer getBuyNumber() {
        return buyNumber;
    }

    /**
     * 设置购买数量
     *
     * @param buyNumber 购买数量
     */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
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
}