package com.itsun.stock.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志
 * @TableName sys_log
 */
public class SysLog implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户操作：DELETE ADD GET UPDATE
     */
    private String operation;

    /**
     * 响应时间,单位毫秒
     */
    private Integer time;

    /**
     * 请求方法（控制层方法全限定名）
     */
    private String method;

    /**
     * 请求参数
     */
    private String params;

    /**
     * IP地址
     */
    private String ip;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", operation='" + operation + '\'' +
                ", time=" + time +
                ", method='" + method + '\'' +
                ", params='" + params + '\'' +
                ", ip='" + ip + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}