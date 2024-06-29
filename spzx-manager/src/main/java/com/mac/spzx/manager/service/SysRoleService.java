package com.mac.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.model.dto.system.SysRoleDto;
import com.mac.spzx.model.entity.system.SysRole;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年06月29日, 16:19:07
 */
public interface SysRoleService {

    // 条件分页查询
    PageInfo<SysRole> getRoleList(Integer currentPage, Integer pageSize, SysRoleDto sysRoleDto);

    // 添加角色
    void saveRole(SysRole sysRole);

    // 修改角色
    void updateRole(SysRole sysRole);

    // 根据id删除角色
    void removeRoleById(Long id);
}