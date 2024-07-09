package com.mac.spzx.manager.service;

import com.mac.spzx.model.entity.system.SysMenu;
import com.mac.spzx.model.vo.system.SysMenuVo;

import java.util.List;
import java.util.Map;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月05日, 21:11:25
 */
public interface SysMenuService {

    // 查询菜单
    List<SysMenu> buildTreeMenu();

    // 新增菜单
    void saveMenu(SysMenu sysMenu);

    // 修改菜单
    void updateMenu(SysMenu sysMenu);

    // 删除菜单
    void deleteMenu(Long id);

    // 查询角色菜单列表
    Map<String, Object> getMenuList(Long roleId);

    // 动态菜单
    List<SysMenuVo> dynamicMenu();
}