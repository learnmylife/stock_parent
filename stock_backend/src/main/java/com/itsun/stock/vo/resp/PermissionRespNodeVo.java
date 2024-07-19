package com.itsun.stock.vo.resp;

import java.util.List;

public class PermissionRespNodeVo {
     private String id;//权限ID
     private String title;//权限标题
     private String icon;//权限图标（按钮权限无图片）
     private String path;//请求地址
     private String name;//权限名称对应前端vue组件名称
     private List<PermissionRespNodeVo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionRespNodeVo> getChildren() {
        return children;
    }

    public void setChildren(List<PermissionRespNodeVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "PermissionRespNodeVo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
