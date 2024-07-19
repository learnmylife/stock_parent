package com.itsun.stock.vo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel
public class LoginRespVo {
    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID", position = 1)
    private String id;
    /**
     * 电话
     */
    @ApiModelProperty(value = "电话", position = 2)
    private String phone;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", position = 3)
    private String username;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", position = 4)
    private String nickName;

    /**
     * 权限树（仅仅显示菜单，不是加载按钮信息）
     */
    @ApiModelProperty(value = "权限树（仅仅显示菜单，不是加载按钮信息）", position = 5)
    private List<PermissionRespNodeVo> menus;

    /**
     * 按钮权限集合
     */
    @ApiModelProperty(value = "按钮权限集合", position = 6)
    private List<String> permissions;

    /**
     * 认证成功后响应的token
     */
    @ApiModelProperty(value = "认证成功后响应的token", position = 7)
    private String accessToken;

    @Override
    public String toString() {
        return "LoginRespVo{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", nickName='" + nickName + '\'' +
                ", menus=" + menus +
                ", permissions=" + permissions +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PermissionRespNodeVo> getMenus() {
        return menus;
    }

    public void setMenus(List<PermissionRespNodeVo> menus) {
        this.menus = menus;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = String.valueOf(id);
    }
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
