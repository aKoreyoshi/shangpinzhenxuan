package com.mac.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.manager.service.SysRoleService;
import com.mac.spzx.model.dto.system.SysRoleDto;
import com.mac.spzx.model.entity.system.SysRole;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.simpleframework.xml.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月29日, 16:17:19
 */
@Tag(name = "角色管理")
@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {


    private SysRoleService sysRoleService;

    @Autowired
    public SysRoleController(SysRoleService sysRoleService) {
        this.sysRoleService = sysRoleService;
    }

    @Operation(summary = "获取角色列表")
    @GetMapping("/getRoleList/{currentPage}/{pageSize}")
    public Result getRoleList(@PathVariable("currentPage") Integer currentPage,
                              @PathVariable("pageSize") Integer pageSize,
                              SysRoleDto sysRoleDto) {
        PageInfo<SysRole> pageInfo = sysRoleService.getRoleList(currentPage, pageSize, sysRoleDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "添加角色")
    @PostMapping("/saveRole")
    public Result saveRole(@RequestBody SysRole sysRole) {
        sysRoleService.saveRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改角色")
    @PutMapping("/updateRole")
    public Result updateRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateRole(sysRole);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/removeRole/{id}")
    public Result removeRole(@PathVariable("id") Long id) {
        sysRoleService.removeRoleById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}