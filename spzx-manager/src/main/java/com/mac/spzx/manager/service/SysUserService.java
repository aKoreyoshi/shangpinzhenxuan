package com.mac.spzx.manager.service;

import com.github.pagehelper.PageInfo;
import com.mac.spzx.model.dto.system.AssignRoleDto;
import com.mac.spzx.model.dto.system.LoginDto;
import com.mac.spzx.model.dto.system.SysUserDto;
import com.mac.spzx.model.entity.system.SysUser;
import com.mac.spzx.model.vo.system.LoginVo;
import com.mac.spzx.model.vo.system.ValidateCodeVo;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年06月26日, 12:01:18
 */
public interface SysUserService {

    // 用户登录
    LoginVo login(LoginDto loginDto);

    // 生成图片验证码
    ValidateCodeVo generateCaptcha();

    // 获取用户信息
    SysUser getUserInfo(String token);

    // 退出登录
    void logout(String token);

    // 获取用户列表
    PageInfo<SysUser> getUserList(Integer currentPage, Integer pageSize, SysUserDto sysUserDto);

    // 新增用户
    void addUser(SysUser sysUser);

    // 修改用户
    void updateUser(SysUser sysUser);

    // 删除用户
    void deleteById(Long id);

    // 分配角色
    void doAssignRole(AssignRoleDto assignRoleDto);
}