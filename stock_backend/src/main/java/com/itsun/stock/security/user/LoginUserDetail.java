package com.itsun.stock.security.user;

import com.itsun.stock.vo.resp.PermissionRespNodeVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class LoginUserDetail implements UserDetails {

    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    //账号没有过期
    private boolean isAccountNonExpired = true;

    //账号没有锁定
    private boolean isAccountNonLocked=true;

    //密码没过期
    private boolean isCredentialsNonExpired=true;

    //账号可用
    private boolean isEnabled=true;

    //自定义字段:

    private String id;
    /**
     * 电话
     */
    private String phone;
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 权限树（仅仅显示菜单，不是加载按钮信息）
     */
    private List<PermissionRespNodeVo> menus;

    /**
     * 按钮权限集合
     */
    private List<String> permissions;
}
