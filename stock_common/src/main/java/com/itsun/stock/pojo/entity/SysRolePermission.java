package com.itsun.stock.pojo.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 角色权限表
 * @TableName sys_role_permission
 */

public class SysRolePermission implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 菜单权限id
     */
    private Long permissionId;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "SysRolePermission{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", permissionId=" + permissionId +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}