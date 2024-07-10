package com.mac.spzx.manager.controller;

import com.mac.spzx.manager.service.UploadService;
import com.mac.spzx.model.vo.common.Result;
import com.mac.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Koreyoshi
 * @description: 该类目前只做用户头像上传的功能
 * @version: 1.0
 * @date: 2024年07月01日, 21:33:02
 */
@Tag(name = "上传头像")
@RestController
@RequestMapping("/admin/system/upload")
public class UploadController {

    private UploadService uploadService;
    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @Operation(summary = "上传头像")
    @PostMapping("/avatarUpload")
    public Result avatarUpload(@RequestParam("avatar") MultipartFile avatar) {
        String url = uploadService.avatarUpload(avatar);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }

    @Operation(summary = "上传品牌图标")
    @PostMapping("/logoUpload")
    public Result logoUpload(@RequestParam("logo") MultipartFile logo) {
        String url = uploadService.avatarUpload(logo);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }


}