package com.xxl.job.executor.domain;

import lombok.Data;
import lombok.ToString;

/**
 * @author Roman.Zhang
 * @date 2020/5/7
 * @description: 数据源公共字段类
 */
@Data
@ToString
public class DatabaseDomain {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
