package com.mac.spzx.manager.controller;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.log.annotation.Log;
import com.mac.spzx.manager.service.SysUserService;
import com.mac.spzx.model.dto.system.AssignRoleDto;
import com.mac.spzx.model.dto.system.SysUserDto;
import com.mac.spzx.model.entity.system.SysUser;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月30日, 16:54:45
 */
@Tag(name = "用户管理")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    private SysUserService sysUserService;

    @Autowired
    public SysUserController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Operation(summary = "条件分页查询")
    @GetMapping("/getUserList/{currentPage}/{pageSize}")
    public Result getUserList(@PathVariable("currentPage") Integer currentPage,
                              @PathVariable("pageSize") Integer pageSize,
                              SysUserDto sysUserDto) {
        PageInfo<SysUser> pageInfo = sysUserService.getUserList(currentPage, pageSize, sysUserDto);
        return Result.build(pageInfo, ResultCodeEnum.SUCCESS);
    }

    @Log(title = "新增用户", businessType = 1) //添加Log注解，设置属性
    @Operation(summary = "新增用户")
    @PostMapping("/addUser")
    public Result addUser(@RequestBody SysUser sysUser) {
        sysUserService.addUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "修改用户")
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody SysUser sysUser) {
        sysUserService.updateUser(sysUser);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "根据id删除用户")
    @DeleteMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable("id") Long id) {
        sysUserService.deleteById(id);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Log(title = "分配角色", businessType = 2)
    @Operation(summary = "分配角色")
    @PostMapping("/doAssignRole")
    public Result doAssignRole(@RequestBody AssignRoleDto assignRoleDto) {
        sysUserService.doAssignRole(assignRoleDto);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}