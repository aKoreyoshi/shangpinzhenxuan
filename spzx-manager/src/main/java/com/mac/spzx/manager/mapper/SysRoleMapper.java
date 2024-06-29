package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.dto.system.SysRoleDto;
import com.mac.spzx.model.entity.system.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年06月29日, 16:44:55
 */
@Mapper
public interface SysRoleMapper {
    List<SysRole> getRoleList(SysRoleDto sysRoleDto);

    void saveRole(SysRole sysRole);

    void updateById(SysRole sysRole);

    void removeById(Long id);
}