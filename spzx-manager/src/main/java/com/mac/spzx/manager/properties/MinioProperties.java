package com.mac.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年07月01日, 22:02:53
 */
@Data
@ConfigurationProperties(prefix = "spzx.minio")
public class MinioProperties {

    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}