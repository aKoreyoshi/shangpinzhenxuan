package com.mac.spzx.util.utils;

import com.mac.spzx.model.entity.system.SysMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月08日, 13:04:35
 */
public class MenuHelper {

    public static List<SysMenu> buildMenuTree(List<SysMenu> sysMenus) {
        List<SysMenu> treeList = new ArrayList<>();
        List<SysMenu> list = sysMenus.stream()
                .filter(sysMenu -> sysMenu.getParentId().longValue() == 0)
                .map(sysMenu -> {
                    sysMenu.setChildren(findChildren(sysMenu, sysMenus));
                    return sysMenu;
                })
                .collect(Collectors.toList());
//        for (SysMenu sysMenu : sysMenus) {
//            if (sysMenu.getParentId().longValue() == 0) {
//                treeList.add(findChildren(sysMenu, sysMenus));
//            }
//        }
        return list;
    }

//    private static SysMenu findChildren(SysMenu sysMenu, List<SysMenu> sysMenus) {
//        sysMenu.setChildren(new ArrayList<>());
//        for (SysMenu menu : sysMenus) {
//            if (menu.getParentId().longValue() == sysMenu.getId().longValue()) {
//                sysMenu.getChildren().add(findChildren(menu, sysMenus));
//            }
//        }
//        return sysMenu;
//    }

    private static List<SysMenu> findChildren(SysMenu sysMenu, List<SysMenu> sysMenus) {
        List<SysMenu> list = sysMenus.stream()
                .filter(menu -> menu.getParentId().longValue() == sysMenu.getId().longValue())
                .map(menu -> {
                    menu.setChildren(findChildren(menu, sysMenus));
                    return menu;
                })
                .collect(Collectors.toList());
        return list;
    }
}