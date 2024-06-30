package com.mac.spzx.manager.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mac.spzx.common.exception.KoreyoshiException;
import com.mac.spzx.manager.mapper.SysUserMapper;
import com.mac.spzx.manager.service.SysUserService;
import com.mac.spzx.model.dto.system.LoginDto;
import com.mac.spzx.model.dto.system.SysUserDto;
import com.mac.spzx.model.entity.system.SysUser;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import com.mac.spzx.model.vo.system.LoginVo;
import com.mac.spzx.model.vo.system.ValidateCodeVo;
import com.mac.spzx.util.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月26日, 12:01:25
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    private SysUserMapper sysUserMapper;
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public SysUserServiceImpl(SysUserMapper sysUserMapper,RedisTemplate<String, String> redisTemplate) {
        this.sysUserMapper = sysUserMapper;
        this.redisTemplate = redisTemplate;
    }

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    @Override
    public LoginVo login(LoginDto loginDto) {
        // 校验验证码
        String captcha = loginDto.getCaptcha();
        String codeKey = loginDto.getCodeKey();
        String code = redisTemplate.opsForValue().get(Constants.SYSUSER_CAPTCHA + codeKey);
        // 比较
        if (StrUtil.isEmpty(code) || !StrUtil.equalsAnyIgnoreCase(code, captcha)) {
            throw new KoreyoshiException(ResultCodeEnum.VALIDATECODE_ERROR);
        }
        // 删除验证码
        redisTemplate.delete(Constants.SYSUSER_CAPTCHA + codeKey);

        // 拿到用户名
        String username = loginDto.getUserName();
        // 根据用户名获取用户信息
        SysUser sysUser = sysUserMapper.selectUserByUserName(username);
        // 判断用户是否为空
        if (sysUser == null) {
            throw new KoreyoshiException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 比较密码
        String inputPassword = loginDto.getPassword();
        String password = sysUser.getPassword();
        // 由于密码是加密的，所以需要先对输入的密码进行加密操作，然后再进行比较
        inputPassword = DigestUtils.md5DigestAsHex(inputPassword.getBytes());
        if (!inputPassword.equals(password)) {
            throw new KoreyoshiException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 登录成功，生成token
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        // 把token存入redis，需要把用户信息转为JSON格式
        String jsonString = JSON.toJSONString(sysUser);
        redisTemplate.opsForValue()
                .set(Constants.SYSUSER_TOKEN + token, jsonString,7, TimeUnit.DAYS);
        // 返回token
        LoginVo loginVo = new LoginVo();
        loginVo.setToken(token);
        return loginVo;
    }


    /**
     * 生成图片验证码。 使用hutool依赖提供的工具类
     * @return
     */
    @Override
    public ValidateCodeVo generateCaptcha() {
        // 生成验证码 通过CaptchaUtil工具类
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(150, 48, 4, 4);

        // 拿到生成的验证码
        String code = captcha.getCode();

        // 将图片验证码进行base64编码
        String imageBase64 = captcha.getImageBase64();

        // 通过UUID生成key作为唯一标识
        String key = UUID.randomUUID().toString().replaceAll("-", "");
        redisTemplate.opsForValue()
                .set(Constants.SYSUSER_CAPTCHA + key, code, 5, TimeUnit.MINUTES);

        // 封装数据返回
        ValidateCodeVo validateCodeVo = new ValidateCodeVo();
        validateCodeVo.setCodeKey(key);
        validateCodeVo.setCodeValue("data:image/png;base64,"+imageBase64);
        return validateCodeVo;
    }

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    @Override
    public SysUser getUserInfo(String token) {
        // 根据token从redis中获取用户信息 此时还是JSON数据需要转换
        String jsonString = redisTemplate.opsForValue().get(Constants.SYSUSER_TOKEN + token);
        if (StrUtil.isEmpty(jsonString)) {
            throw new KoreyoshiException(ResultCodeEnum.LOGIN_AUTH);
        }
        SysUser sysUser = JSON.parseObject(jsonString, SysUser.class);
        return sysUser;
    }

    /**
     * 退出登录
     * @param token
     */
    @Override
    public void logout(String token) {
        // 退出登录只需要删除Redis中的用户信息
        redisTemplate.delete(Constants.SYSUSER_TOKEN + token);
    }

    /**
     * 获取用户列表
     * @param currentPage
     * @param pageSize
     * @param sysUserDto
     * @return
     */
    @Override
    public PageInfo<SysUser> getUserList(Integer currentPage, Integer pageSize, SysUserDto sysUserDto) {
        // 开启分页
        PageHelper.startPage(currentPage, pageSize);
        // 查询用户列表
        List<SysUser> userList = sysUserMapper.selectUserList(sysUserDto);
        // 获取分页信息
        PageInfo<SysUser> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    /**
     * 添加用户
     * @param sysUser
     */
    @Override
    public void addUser(SysUser sysUser) {
        // 先根据用户名查询用户是否存在
        SysUser user = sysUserMapper.selectUserByUserName(sysUser.getUserName());
        if (user != null) {
            throw new KoreyoshiException(ResultCodeEnum.USER_NAME_IS_EXISTS);
        }
        // 对密码做加密处理
        String password = sysUser.getPassword();
        String MD5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        sysUser.setPassword(MD5Password);
        // TODO 为什么状态设置为0？
        sysUser.setStatus(1);
        // 执行添加操作
        sysUserMapper.insertUser(sysUser);
    }

    /**
     * 修改用户信息
     * @param sysUser
     */
    @Override
    public void updateUser(SysUser sysUser) {
        // 对密码做加密处理
//        String password = sysUser.getPassword();
//        String MD5Password = DigestUtils.md5DigestAsHex(password.getBytes());
//        sysUser.setPassword(MD5Password);
        // 执行修改操作
        sysUserMapper.updateById(sysUser);
    }

    /**
     * 删除用户 根据id
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        sysUserMapper.deleteById(id);
    }
}