package com.itsun.stock.pojo.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 权限表（菜单）
 * @TableName sys_permission
 */
public class SysPermission implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 菜单权限编码(前端按钮权限标识)
     */
    private String code;

    /**
     * 菜单权限名称
     */
    private String title;

    /**
     * 菜单图标(侧边导航栏图标)
     */
    private String icon;

    /**
     * SpringSecurity授权标识(如：sys:user:add)
     */
    private String perms;

    /**
     * 访问地址URL
     */
    private String url;

    /**
     * 资源请求类型
     */
    private String method;

    /**
     * name与前端vue路由name约定一致
     */
    private String name;

    /**
     * 父级菜单权限id，pid等于0 为顶层权限
     */
    private Long pid;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 菜单权限类型(1:目录;2:菜单;3:按钮)
     */
    private Integer type;

    /**
     * 状态1:正常 0：禁用
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除(1未删除；0已删除)
     */
    private Integer deleted;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "SysPermission{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", perms='" + perms + '\'' +
                ", url='" + url + '\'' +
                ", method='" + method + '\'' +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", orderNum=" + orderNum +
                ", type=" + type +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", deleted=" + deleted +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}