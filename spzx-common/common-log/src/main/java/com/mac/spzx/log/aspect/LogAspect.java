package com.mac.spzx.log.aspect;

import com.mac.spzx.log.annotation.Log;
import com.mac.spzx.log.service.AsyncOperLogService;
import com.mac.spzx.log.utils.LogUtil;
import com.mac.spzx.model.entity.system.SysOperLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月14日, 15:45:35
 */

@Aspect
@Component
@Slf4j
public class LogAspect {

    private AsyncOperLogService asyncOperLogService ;
    @Autowired
    public LogAspect(AsyncOperLogService asyncOperLogService) {
        this.asyncOperLogService = asyncOperLogService;
    }

    // 环绕通知切面类定义
    @Around(value = "@annotation(sysLog)")
    public Object around(ProceedingJoinPoint joinPoint, Log sysLog) {
        SysOperLog sysOperLog = new SysOperLog();
        // 前置方法
        LogUtil.beforeHandleLog(sysLog, joinPoint, sysOperLog);
        // 执行方法
        Object result = null;
        try {
            result = joinPoint.proceed();
            // 后置方法
            LogUtil.afterHandlLog(sysLog,result,sysOperLog,0,null);
        } catch (Throwable e) {
            e.printStackTrace();
            // 后置方法
            LogUtil.afterHandlLog(sysLog,result,sysOperLog,1,e.getMessage());
            throw new RuntimeException(e);
        }
        // 保存日志
        asyncOperLogService.saveSysOperLog(sysOperLog);
    return result;
    }
}