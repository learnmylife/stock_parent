package com.itsun.stock.service;

import com.google.common.base.Strings;
import com.itsun.stock.mapper.SysRoleMapper;
import com.itsun.stock.mapper.SysUserMapper;
import com.itsun.stock.pojo.entity.SysPermission;
import com.itsun.stock.pojo.entity.SysRole;
import com.itsun.stock.pojo.entity.SysUser;
import com.itsun.stock.security.user.LoginUserDetail;
import com.itsun.stock.vo.resp.PermissionRespNodeVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 获取用户Bean
 */
@Service("userDetailsService")
public class LoginUserDetailService implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private PremissionService premissionService;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    /**
     * 根据用户名加载权限
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser dbName = sysUserMapper.findUserByUsername(username);
        if (dbName==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //组装
        //获取用户权限集合
        Long id = dbName.getId();
        List<SysPermission> permissions = premissionService.getPermissionByUserId(String.valueOf(id));
        List<SysRole> roles = sysRoleMapper.getRoleByUserId(id);
        //获取树状权限菜单数据
        List<PermissionRespNodeVo> tree=premissionService.getTree(permissions, "0", true);
        //获取菜单按钮集合
        List<String> authBtnPerms = permissions.stream().filter(per -> !Strings.isNullOrEmpty(per.getCode()) && per.getType() == 3).map(per -> per.getCode()).collect(Collectors.toList());

        ArrayList<String> strings = new ArrayList<>();

        //获取springSecurity权限表示
        List<String> collect = permissions.stream()
                .filter(per -> StringUtils.isNoneBlank(per.getPerms()))
                .map(per -> per.getPerms())
                .collect(Collectors.toList());
        List<String> collect1 = roles.stream().map(r -> "ROLE" + r.getName()).collect(Collectors.toList());

        strings.addAll(collect);
        strings.addAll(collect1);
        //将用户权限,转换为Authority表示
        String[] strings1 = strings.toArray(new String[collect.size()]);
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(strings1);

        //构建用户详情对象
        LoginUserDetail loginUserDetail = new LoginUserDetail();
        BeanUtils.copyProperties(dbName,loginUserDetail);
        loginUserDetail.setAuthorities(authorityList);
        loginUserDetail.setMenus(tree);
        loginUserDetail.setPermissions(authBtnPerms);

        return loginUserDetail;
    }
}
