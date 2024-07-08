package com.itsun.stock.mapper;

import com.itsun.stock.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
* @author sunhb
* @description 针对表【sys_user(用户表)】的数据库操作Mapper
* @createDate 2024-07-07 17:59:11
* @Entity com.itsun.stock.pojo.entity.SysUser
*/
public interface SysUserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    SysUser findUserByUsername(@Param("userName") String username);

}
