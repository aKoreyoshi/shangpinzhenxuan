package com.mac.spzx.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Koreyoshi
 * @function: swagger配置项
 * @version: 1.0
 * @date: 2024年06月26日, 11:08:31
 */
@Configuration
public class Knife4jConfig {

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .pathsToMatch("/admin/**")  // 需要扫描的路径
                .group("admin-api")         // 分组名称
                .build();
    }

    @Bean
    public OpenAPI descriptionAPI() {
        return new OpenAPI().info(new Info()
                .title("尚品甄选API接口文档")
                .version("1.0")
                .description("尚品甄选API接口文档")
                .contact(new Contact()
                        .name("Koreyoshi")
                        .email("macong0419email@163.com")
                        .url("https://github.com/aKoreyoshi/shangpinzhenxuan")));
    }
}