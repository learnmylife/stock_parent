package com.itsun.stock.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName sys_user
 */
public class SysUser implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 账户
     */
    private String username;

    /**
     * 用户密码密文
     */
    private String password;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 真实名称
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 邮箱(唯一)
     */
    private String email;

    /**
     * 账户状态(1.正常 2.锁定 )
     */
    private Integer status;

    /**
     * 性别(1.男 2.女)
     */
    private Integer sex;

    /**
     * 是否删除(1未删除；0已删除)
     */
    private Integer deleted;

    /**
     * 创建人
     */
    private Long createId;

    /**
     * 更新人
     */
    private Long updateId;

    /**
     * 创建来源(1.web 2.android 3.ios )
     */
    private Integer createWhere;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", sex=" + sex +
                ", deleted=" + deleted +
                ", createId=" + createId +
                ", updateId=" + updateId +
                ", createWhere=" + createWhere +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public Long getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    public Integer getCreateWhere() {
        return createWhere;
    }

    public void setCreateWhere(Integer createWhere) {
        this.createWhere = createWhere;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}