package com.mac.spzx.manager.controller;

import com.mac.spzx.manager.service.SysMenuService;
import com.mac.spzx.model.entity.system.SysMenu;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @Operation(description = "构建菜单树形列表")
    @GetMapping("/buildTreeMenu")
    public Result buildTreeMenu() {
        List<SysMenu> sysMenus = sysMenuService.buildTreeMenu();
        return Result.build(sysMenus, ResultCodeEnum.SUCCESS);
    }


    @Operation(summary = "添加菜单")
    @PostMapping("/saveMenu")
    public Result saveMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.saveMenu(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改菜单")
    @PutMapping("/updateMenu")
    public Result updateMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateMenu(sysMenu);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除菜单")
    @DeleteMapping("/deleteMenu/{id}")
    public Result deleteMenu(@PathVariable("id") Long id) {
        sysMenuService.deleteMenu(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "获取角色的菜单列表")
    @GetMapping("/getMenuList/{roleId}")
    public Result getMenuList(@PathVariable("roleId") Long roleId) {
        Map<String, Object> map = sysMenuService.getMenuList(roleId);
        return Result.build(map, ResultCodeEnum.SUCCESS);
    }


}