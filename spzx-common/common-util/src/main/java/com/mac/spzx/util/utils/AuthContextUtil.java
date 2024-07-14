package com.mac.spzx.util.utils;

import com.mac.spzx.model.entity.system.SysUser;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月27日, 21:52:19
 */
public class AuthContextUtil {

    private static final ThreadLocal<SysUser> threadlocal = new ThreadLocal<>();


    public static void set(SysUser sysUser){
        threadlocal.set(sysUser);
    }

    public static SysUser get(){
        return threadlocal.get();
    }


    public static void remove(){
        threadlocal.remove();
    }
}