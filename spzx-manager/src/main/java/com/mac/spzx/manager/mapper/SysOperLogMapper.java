package com.mac.spzx.manager.mapper;

import com.mac.spzx.model.entity.system.SysOperLog;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: Koreyoshi
 * @version: 1.0
 * @date: 2024年07月14日, 16:43:01
 */
@Mapper
public interface SysOperLogMapper {

    void insert(SysOperLog sysOperLog);
}