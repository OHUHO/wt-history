package com.history.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description: WebMvcConfig
 * @author: Walker
 * @date: 2024-06-26 23:13:13
 * @version: 1.0.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${storage.path}")
    private String path;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:"+ path);
    }

}
