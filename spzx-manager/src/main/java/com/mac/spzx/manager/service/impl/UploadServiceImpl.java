package com.mac.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.mac.spzx.manager.properties.MinioProperties;
import com.mac.spzx.manager.service.UploadService;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月01日, 21:34:33
 */
@Service
public class UploadServiceImpl implements UploadService {

    private MinioProperties minioProperties;

    @Autowired
    public UploadServiceImpl(MinioProperties minioProperties) {
        this.minioProperties = minioProperties;
    }

    /**
     * 用户头像上传
     *
     * @param file
     * @return
     */
    @Override
    public String avatarUpload(MultipartFile file) {
        try {
            // 创建一个Minio客户端对象
            MinioClient minioClient = new MinioClient.Builder()
                    .endpoint(minioProperties.getEndpoint())
                    .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                    .build();
            // 判断桶是否存在，不存在则创建
            boolean bucketExists = minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!bucketExists) {
                minioClient.makeBucket(
                        MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            } else {
                // 存在，打印信息
                System.out.println("Bucket " + minioProperties.getBucketName() + " already exists");
            }
            // 对图片进行处理： 1 通过日期分类  2 文件名uuid随机
            String dateString = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String filename = file.getOriginalFilename();
            // 拼接
            filename = dateString + "/" + uuid + filename;
            // 文件上传
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .stream(file.getInputStream(), file.getSize(), -1)
                    .object(filename)
                    .build();
            minioClient.putObject(putObjectArgs);
            // 返回图片访问地址
            return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName()
                    + "/" + filename;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}