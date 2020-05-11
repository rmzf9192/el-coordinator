package com.xxl.job.executor.core.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Roman.Zhang
 * @date 2020/5/11
 * @description: 获取指定路径
 */
@Configuration
@Data
public class ReadPathJsonFileConfig {

    @Value("${coordinator.path.json}")
    private String path;
}
