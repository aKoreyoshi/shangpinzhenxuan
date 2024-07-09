package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.entity.system.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月05日, 21:11:54
 */
@Mapper
public interface SysMenuMapper {
    List<SysMenu> findAll();

    void saveMenu(SysMenu sysMenu);

    void updateMenu(SysMenu sysMenu);

    Integer selectChildMenu(Long id);

    void deleteById(Long id);

    List<SysMenu> selectByUserId(Long userId);

    SysMenu selectParentMenu(Long parentId);

    void updateIsHalf(Long menuId);

}