package com.mac.spzx.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mac.spzx.manager.mapper.SysRoleMapper;
import com.mac.spzx.manager.mapper.SysUserRoleMapper;
import com.mac.spzx.manager.service.SysRoleService;
import com.mac.spzx.model.dto.system.SysRoleDto;
import com.mac.spzx.model.entity.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月29日, 16:19:20
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    private SysRoleMapper sysRoleMapper;
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    public SysRoleServiceImpl(SysRoleMapper sysRoleMapper,
                              SysUserRoleMapper sysUserRoleMapper) {
        this.sysRoleMapper = sysRoleMapper;
        this.sysUserRoleMapper = sysUserRoleMapper;
    }

    /**
     * 条件分页查询
     * @param currentPage
     * @param pageSize
     * @param sysRoleDto
     * @return
     */
    @Override
    public PageInfo<SysRole> findRolePage(Integer currentPage, Integer pageSize, SysRoleDto sysRoleDto) {
        // 开启分页
        PageHelper.startPage(currentPage, pageSize);
        // 查询
        List<SysRole> sysRoles = sysRoleMapper.findRolePage(sysRoleDto);
        // 封装分页对象
        PageInfo<SysRole> page = new PageInfo<>(sysRoles);
        return page;
    }

    /**
     * 添加角色
     * @param sysRole
     */
    @Override
    public void saveRole(SysRole sysRole) {
        sysRoleMapper.saveRole(sysRole);
    }

    /**
     * 修改角色
     * @param sysRole
     */
    @Override
    public void updateRole(SysRole sysRole) {
        sysRoleMapper.updateById(sysRole);
    }

    @Override
    public void removeRoleById(Long id) {
        sysRoleMapper.removeById(id);
    }

    @Override
    public Map<String, Object> getRoleList(Long userId) {
        // 获取到所有的角色列表
        List<SysRole> sysRoles = sysRoleMapper.getRoleList();
        // 获取已经分配过的角色id
        List<Long> roleIds = sysUserRoleMapper.getRoleIdsByUserId(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("roleList", sysRoles);
        map.put("roleIds", roleIds);
        return map;
    }
}