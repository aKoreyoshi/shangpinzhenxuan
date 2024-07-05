package com.mac.spzx.manager.service.impl;

import com.mac.spzx.manager.mapper.SysMenuMapper;
import com.mac.spzx.manager.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月05日, 21:11:36
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    private SysMenuMapper sysMenuMapper;
    @Autowired
    public void setSysMenuMapper(SysMenuMapper sysMenuMapper) {
        this.sysMenuMapper = sysMenuMapper;
    }

}