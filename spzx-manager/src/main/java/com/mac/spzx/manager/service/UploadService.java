package com.mac.spzx.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月01日, 21:34:20
 */
public interface UploadService {

    // 用户头像上传
    String avatarUpload(MultipartFile file);
}