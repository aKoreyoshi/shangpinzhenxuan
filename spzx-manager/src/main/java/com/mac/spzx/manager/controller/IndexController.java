package com.mac.spzx.manager.controller;

import com.mac.spzx.manager.service.SysUserService;
import com.mac.spzx.model.dto.system.LoginDto;
import com.mac.spzx.model.entity.system.SysUser;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import com.mac.spzx.model.vo.system.LoginVo;
import com.mac.spzx.model.vo.system.ValidateCodeVo;
import com.mac.spzx.util.utils.AuthContextUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月26日, 12:00:06
 */
@Tag(name = "用户登录")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    private SysUserService sysUserService;
    @Autowired
    public IndexController(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody LoginDto loginDto) {
        LoginVo loginVo = sysUserService.login(loginDto);
        return Result.build(loginVo, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "生成图片验证码")
    @GetMapping("/generateCaptcha")
    public Result generateCaptcha() {
        ValidateCodeVo validateCodeVo =  sysUserService.generateCaptcha();
        return Result.build(validateCodeVo, ResultCodeEnum.SUCCESS);
    }



//    @Operation(summary = "获取用户信息")
//    @GetMapping("/getUserInfo")
//    public Result getUserInfo(@RequestHeader(name = "token") String token) {
//        SysUser sysUser = sysUserService.getUserInfo(token);
//        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
//    }

    // TODO 获取用户信息这里，由于引入了ThreadLocal，可以优化 直接从ThreadLocal中获取用户信息
    @Operation(summary = "获取用户信息")
    @GetMapping("/getUserInfo")
    public Result getUserInfo() {
        SysUser sysUser = AuthContextUtil.get();
        return Result.build(sysUser, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "退出登录")
    @GetMapping("/logout")
    public Result logout(@RequestHeader(name = "token") String token) {
        sysUserService.logout(token);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }
}