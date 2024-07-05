package com.mac.spzx.manager.controller;

import com.mac.spzx.manager.service.SysMenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月05日, 21:10:41
 */
@Tag(name = "菜单管理")
@RestController
@RequestMapping("/admin/system/sysMenu")
public class SysMenuController {

    private SysMenuService sysMenuService;
    @Autowired
    public SysMenuController(SysMenuService sysMenuService) {
        this.sysMenuService = sysMenuService;
    }
}