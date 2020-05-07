package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.executor.core.config.XxlJobReaderDatabaseConfig;
import com.xxl.job.executor.core.config.XxlJobWriterDatabaseConfig;
import com.xxl.job.executor.service.rpc.DataXExecuteServiceInterface;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Roman.Zhang
 * @date 2020/5/7
 * @description:
 */
@Component
@EnableConfigurationProperties({XxlJobReaderDatabaseConfig.class, XxlJobWriterDatabaseConfig.class})
@RequiredArgsConstructor
public class XxxlJobExecutorDataxJob {
    private static Logger logger = LoggerFactory.getLogger(XxxlJobExecutorDataxJob.class);
    //读数据源信息
    private final XxlJobReaderDatabaseConfig xxlJobDatabaseConfig;
    //写数据源信息
    private final XxlJobWriterDatabaseConfig xxlJobWriterDatabaseConfig;

    @Autowired
    final DataXExecuteServiceInterface dataXExecuteInterface;

    @XxlJob("dataxJobDemo")
    public ReturnT<String> dataxJobDemo(String param) throws Exception {
        //1.获取数据源信息
        XxlJobLogger.log(xxlJobDatabaseConfig.toString());
        XxlJobLogger.log(xxlJobWriterDatabaseConfig.toString());
        XxlJobLogger.log("XXL-JOB, Hello World."+param);
        //1.1将需要发送的数据备份到日志表中

        //2.设置同步属性（是datax的属性，暂时不设）

        //3.请求el-data-turbine 同时传递回调接口
        String callBackMsg = dataXExecuteInterface.sayHello("Hello Datax quick come on ");
        logger.info(callBackMsg);

        return ReturnT.SUCCESS;
    }
}
