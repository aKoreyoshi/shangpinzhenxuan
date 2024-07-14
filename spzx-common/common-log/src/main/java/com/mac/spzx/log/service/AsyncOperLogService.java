package com.mac.spzx.log.service;

import com.mac.spzx.model.entity.system.SysOperLog;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月14日, 16:00:07
 */
public interface AsyncOperLogService {

    // 保存日志数据
    void saveSysOperLog(SysOperLog sysOperLog) ;
}