package com.itsun.stock.service;

import com.itsun.stock.pojo.entity.SysPermission;
import com.itsun.stock.vo.resp.PermissionRespNodeVo;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PremissionService {
    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    List<SysPermission> getPermissionByUserId(@Param("userId") String userId);

    /**
     * @param permissions 权限树状集合
     * @param pid 权限父id，顶级权限的pid默认为0
     * @param isOnlyMenuType true:遍历到菜单，  false:遍历到按钮
     * type: 目录1 菜单2 按钮3
     * @return
     */
    List<PermissionRespNodeVo> getTree(List<SysPermission> permissions, String pid, boolean isOnlyMenuType);
}
