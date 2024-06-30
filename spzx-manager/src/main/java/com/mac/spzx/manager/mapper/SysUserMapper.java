package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.dto.system.SysUserDto;
import com.mac.spzx.model.entity.system.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月26日, 12:00:18
 */
@Mapper
public interface SysUserMapper {
    SysUser selectUserByUserName(String username);

    List<SysUser> selectUserList(SysUserDto sysUserDto);

    void insertUser(SysUser sysUser);

    void updateById(SysUser sysUser);

    void deleteById(Long id);
}