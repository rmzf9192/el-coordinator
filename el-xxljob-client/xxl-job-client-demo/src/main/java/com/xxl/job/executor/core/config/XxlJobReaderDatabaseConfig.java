package com.xxl.job.executor.core.config;

import com.xxl.job.executor.domain.DatabaseDomain;
import lombok.Data;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Roman.Zhang
 * @date 2020/5/7
 * @description: 读取数据源
 */
@ConfigurationProperties(prefix = "xxl.job.read.database")
public class XxlJobReaderDatabaseConfig extends DatabaseDomain {

}
