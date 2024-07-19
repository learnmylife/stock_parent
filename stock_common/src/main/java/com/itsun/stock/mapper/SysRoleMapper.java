package com.itsun.stock.mapper;

import com.itsun.stock.pojo.entity.SysRole;

import java.util.List;

/**
* @author sunhb
* @description 针对表【sys_role(角色表)】的数据库操作Mapper
* @createDate 2024-07-07 17:59:11
* @Entity com.itsun.stock.pojo.entity.SysRole
*/
public interface SysRoleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> getRoleByUserId(Long id);
}
