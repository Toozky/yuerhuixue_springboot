package com.yuerhuixue.pojo;

import java.util.Date;
import javax.persistence.*;

public class Admins {
    /**
     * 管理员id
     */
    @Id
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 管理员登录名
     */
    @Column(name = "admin_name")
    private String adminName;

    /**
     * 管理员密码
     */
    @Column(name = "admin_pwd")
    private String adminPwd;

    /**
     * 管理员昵称
     */
    @Column(name = "admin_nickname")
    private String adminNickname;

    /**
     * 管理员头像
     */
    @Column(name = "admin_img")
    private String adminImg;

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
     * 获取管理员id
     *
     * @return admin_id - 管理员id
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置管理员id
     *
     * @param adminId 管理员id
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取管理员登录名
     *
     * @return admin_name - 管理员登录名
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * 设置管理员登录名
     *
     * @param adminName 管理员登录名
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * 获取管理员密码
     *
     * @return admin_pwd - 管理员密码
     */
    public String getAdminPwd() {
        return adminPwd;
    }

    /**
     * 设置管理员密码
     *
     * @param adminPwd 管理员密码
     */
    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    /**
     * 获取管理员昵称
     *
     * @return admin_nickname - 管理员昵称
     */
    public String getAdminNickname() {
        return adminNickname;
    }

    /**
     * 设置管理员昵称
     *
     * @param adminNickname 管理员昵称
     */
    public void setAdminNickname(String adminNickname) {
        this.adminNickname = adminNickname;
    }

    /**
     * 获取管理员头像
     *
     * @return admin_img - 管理员头像
     */
    public String getAdminImg() {
        return adminImg;
    }

    /**
     * 设置管理员头像
     *
     * @param adminImg 管理员头像
     */
    public void setAdminImg(String adminImg) {
        this.adminImg = adminImg;
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