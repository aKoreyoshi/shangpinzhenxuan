package com.mac.spzx.manager.service.impl;

import com.mac.spzx.log.service.AsyncOperLogService;
import com.mac.spzx.manager.mapper.SysOperLogMapper;
import com.mac.spzx.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月14日, 16:38:07
 */
@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {

    private SysOperLogMapper sysOperLogMapper;
    @Autowired
    public AsyncOperLogServiceImpl(SysOperLogMapper sysOperLogMapper) {
        this.sysOperLogMapper = sysOperLogMapper;
    }
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }
}