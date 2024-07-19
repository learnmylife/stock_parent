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



    /**
     * 用户id
     */
    public Long getId() {
        return id;
    }

    /**
     * 用户id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 账户
     */
    public String getUsername() {
        return username;
    }

    /**
     * 账户
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 用户密码密文
     */
    public String getPassword() {
        return password;
    }

    /**
     * 用户密码密文
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 真实名称
     */
    public String getRealName() {
        return realName;
    }

    /**
     * 真实名称
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * 昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 邮箱(唯一)
     */
    public String getEmail() {
        return email;
    }

    /**
     * 邮箱(唯一)
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 账户状态(1.正常 2.锁定 )
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 账户状态(1.正常 2.锁定 )
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 性别(1.男 2.女)
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 性别(1.男 2.女)
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 是否删除(1未删除；0已删除)
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 是否删除(1未删除；0已删除)
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 创建人
     */
    public Long getCreateId() {
        return createId;
    }

    /**
     * 创建人
     */
    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    /**
     * 更新人
     */
    public Long getUpdateId() {
        return updateId;
    }

    /**
     * 更新人
     */
    public void setUpdateId(Long updateId) {
        this.updateId = updateId;
    }

    /**
     * 创建来源(1.web 2.android 3.ios )
     */
    public Integer getCreateWhere() {
        return createWhere;
    }

    /**
     * 创建来源(1.web 2.android 3.ios )
     */
    public void setCreateWhere(Integer createWhere) {
        this.createWhere = createWhere;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}