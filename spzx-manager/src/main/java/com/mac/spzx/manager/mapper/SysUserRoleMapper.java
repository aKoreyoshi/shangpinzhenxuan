package com.mac.spzx.manager.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月05日, 20:21:30
 */
@Mapper
public interface SysUserRoleMapper {
    void deleteByUserId(Long userId);

    void assignRole(Long userId, Long roleId);

    List<Long> getRoleIdsByUserId(Long userId);
}