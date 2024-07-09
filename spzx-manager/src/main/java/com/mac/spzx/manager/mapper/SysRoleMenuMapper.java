package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.dto.system.AssignMenuDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月08日, 20:45:01
 */
@Mapper
public interface SysRoleMenuMapper {
    List<Long> selectByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);

    void assignMenu(AssignMenuDto assignMenuDto);
}