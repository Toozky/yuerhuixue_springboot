package com.yuerhuixue.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "shopping_cart")
public class ShoppingCart {
    /**
     * 购物车id
     */
    @Id
    @Column(name = "cart_id")
    private Integer cartId;

    /**
     * 乐器id
     */
    @Column(name = "ins_id")
    private Integer insId;

    /**
     * 乐器名
     */
    @Column(name = "ins_name")
    private String insName;

    /**
     * 乐器图片
     */
    @Column(name = "ins_img")
    private String insImg;

    /**
     * 购物车数量
     */
    @Column(name = "cart_number")
    private Integer cartNumber;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 添加时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取购物车id
     *
     * @return cart_id - 购物车id
     */
    public Integer getCartId() {
        return cartId;
    }

    /**
     * 设置购物车id
     *
     * @param cartId 购物车id
     */
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

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
     * 获取乐器名
     *
     * @return ins_name - 乐器名
     */
    public String getInsName() {
        return insName;
    }

    /**
     * 设置乐器名
     *
     * @param insName 乐器名
     */
    public void setInsName(String insName) {
        this.insName = insName;
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
     * 获取购物车数量
     *
     * @return cart_number - 购物车数量
     */
    public Integer getCartNumber() {
        return cartNumber;
    }

    /**
     * 设置购物车数量
     *
     * @param cartNumber 购物车数量
     */
    public void setCartNumber(Integer cartNumber) {
        this.cartNumber = cartNumber;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
}