package com.itsun.stock.service.Impl;

import com.google.common.collect.Lists;
import com.itsun.stock.mapper.SysPermissionMapper;
import com.itsun.stock.pojo.entity.SysPermission;
import com.itsun.stock.service.PremissionService;
import com.itsun.stock.vo.resp.PermissionRespNodeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class PremissionServiceImpl implements PremissionService {
    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @Override
    public List<SysPermission> getPermissionByUserId(String userId) {
        return sysPermissionMapper.getPermissionByUserId(userId);
    }

    /**
     * @param permissions 权限树状集合
     * @param pid 权限父id，顶级权限的pid默认为0
     * @param isOnlyMenuType true:遍历到菜单，  false:遍历到按钮
     * type: 目录1 菜单2 按钮3
     * @return
     */
    @Override
    public List<PermissionRespNodeVo> getTree(List<SysPermission> permissions, String pid, boolean isOnlyMenuType) {
        ArrayList<PermissionRespNodeVo> list = Lists.newArrayList();
        //如果为空
        if (CollectionUtils.isEmpty(permissions)) {
            return list;
        }
        //遍历权限树状集合
        for (SysPermission permission : permissions) {
            Long pid1 = permission.getPid();
            //如果权限 父id(pid) 相等
            if (String.valueOf(permission.getPid()).equals(pid)) {
                if (permission.getType().intValue()!=3 || !isOnlyMenuType) {
                    PermissionRespNodeVo respNodeVo = new PermissionRespNodeVo();
                    respNodeVo.setId(String.valueOf(permission.getId()));
                    respNodeVo.setTitle(permission.getTitle());
                    respNodeVo.setIcon(permission.getIcon());
                    respNodeVo.setPath(permission.getUrl());
                    respNodeVo.setName(permission.getName());
                    respNodeVo.setChildren(getTree(permissions, String.valueOf(permission.getId()),isOnlyMenuType));
                    list.add(respNodeVo);
                }
            }
        }
        return list;
    }
}
