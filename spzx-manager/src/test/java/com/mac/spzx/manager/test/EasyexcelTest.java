package com.mac.spzx.manager.test;

import com.alibaba.excel.EasyExcel;
import com.mac.spzx.manager.mapper.SysUserMapper;
import com.mac.spzx.model.entity.system.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月10日, 09:23:28
 */
@SpringBootTest
public class EasyexcelTest {

    private SysUserMapper sysUserMapper;
    @Autowired
    public EasyexcelTest(SysUserMapper sysUserMapper) {
        this.sysUserMapper = sysUserMapper;
    }

    @Test
    public void test() {
        List<SysUser> sysUsers = sysUserMapper.selectUserList(null);
        EasyExcel.write("d://user.xlsx", SysUser.class).sheet().doWrite(sysUsers);
    }
}