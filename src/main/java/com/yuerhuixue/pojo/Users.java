package com.yuerhuixue.pojo;

import java.util.Date;
import javax.persistence.*;

public class Users {
    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户登录名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户密码
     */
    @Column(name = "user_pwd")
    private String userPwd;

    /**
     * 用户昵称
     */
    @Column(name = "user_nickname")
    private String userNickname;

    /**
     * 用户性别
     */
    @Column(name = "user_gender")
    private String userGender;

    /**
     * 用户年龄
     */
    @Column(name = "user_age")
    private String userAge;

    /**
     * 用户电话
     */
    @Column(name = "user_tel")
    private String userTel;

    /**
     * 用户邮箱
     */
    @Column(name = "user_email")
    private String userEmail;

    /**
     * 用户头像
     */
    @Column(name = "user_img")
    private String userImg;

    /**
     * 注册时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * 获取用户登录名
     *
     * @return user_name - 用户登录名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户登录名
     *
     * @param userName 用户登录名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取用户密码
     *
     * @return user_pwd - 用户密码
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * 设置用户密码
     *
     * @param userPwd 用户密码
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * 获取用户昵称
     *
     * @return user_nickname - 用户昵称
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * 设置用户昵称
     *
     * @param userNickname 用户昵称
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    /**
     * 获取用户性别
     *
     * @return user_gender - 用户性别
     */
    public String getUserGender() {
        return userGender;
    }

    /**
     * 设置用户性别
     *
     * @param userGender 用户性别
     */
    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    /**
     * 获取用户年龄
     *
     * @return user_age - 用户年龄
     */
    public String getUserAge() {
        return userAge;
    }

    /**
     * 设置用户年龄
     *
     * @param userAge 用户年龄
     */
    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    /**
     * 获取用户电话
     *
     * @return user_tel - 用户电话
     */
    public String getUserTel() {
        return userTel;
    }

    /**
     * 设置用户电话
     *
     * @param userTel 用户电话
     */
    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    /**
     * 获取用户邮箱
     *
     * @return user_email - 用户邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 设置用户邮箱
     *
     * @param userEmail 用户邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * 获取用户头像
     *
     * @return user_img - 用户头像
     */
    public String getUserImg() {
        return userImg;
    }

    /**
     * 设置用户头像
     *
     * @param userImg 用户头像
     */
    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    /**
     * 获取注册时间
     *
     * @return create_time - 注册时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置注册时间
     *
     * @param createTime 注册时间
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

    @Override
    public String toString() {
        return "Users{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userAge='" + userAge + '\'' +
                ", userTel='" + userTel + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userImg='" + userImg + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}