package com.mac.spzx.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @author: Koreyoshi
 * @description:
 * @version: 1.0
 * @date: 2024年06月29日, 14:34:55
 */
@Data
@ConfigurationProperties(prefix = "spzx.auth")
public class UserAuthProperties {


    private List<String> noAuthUrls;
}