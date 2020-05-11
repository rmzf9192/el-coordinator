package com.xxl.job.executor.service.jobhandler;

import com.el.trubine.rpc.common.bean.DataxResult;
import com.el.trubine.rpc.common.bean.DateXExecuteParameter;
import com.el.trubine.rpc.common.service.DataXExecuteServiceInterface;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.IpUtil;
import com.xxl.job.executor.core.config.ReadPathJsonFileConfig;
import com.xxl.job.executor.core.config.XxlJobReaderDatabaseConfig;
import com.xxl.job.executor.core.config.XxlJobWriterDatabaseConfig;
import com.xxl.job.executor.domain.LogRecord;
import com.xxl.job.executor.mapper.LogMapperDao;
import com.xxl.job.executor.utils.FindFileUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;


/**
 * @author Roman.Zhang
 * @date 2020/5/7
 * @description:
 */
@Slf4j
@Component
@EnableConfigurationProperties({XxlJobReaderDatabaseConfig.class, XxlJobWriterDatabaseConfig.class})
@RequiredArgsConstructor
public class XxxlJobExecutorDataxJob {
    private static Logger logger = LoggerFactory.getLogger(XxxlJobExecutorDataxJob.class);
    public static final long PRE_READ_MS = 5000;    // pre read
    //读数据源信息
    private final XxlJobReaderDatabaseConfig xxlJobDatabaseConfig;
    //写数据源信息
    private final XxlJobWriterDatabaseConfig xxlJobWriterDatabaseConfig;

    private final ReadPathJsonFileConfig readPathJsonFileConfig;

    @Autowired
    private LogMapperDao logMapperDao;
//    @Autowired
//    private XxlJobInfoDao xxlJobInfoDao;
    @Autowired
    private DataXExecuteServiceInterface dataXExecuteServiceInterface;

    /**
     * 针对场景为：任务为：阻塞处理策略，以避免过多请求
     * @param param 必须保持与任务ID一致
     * @return
     * @throws Exception
     */
    @XxlJob("dataxJobDemo")
    @Transactional
    public ReturnT<String> dataxJobDemo(String param) throws Exception {
        LogRecord logRecord = new LogRecord();
        try{
            //根据uuid对发送到El-Data-Turbine任务做标识
            String uuid = UUID.randomUUID().toString().replace("-", "");


            logRecord.setDataxId(uuid);
            logRecord.setJobId(param);
            logRecord.setLogType("S");//S:成功，F:失败

            String hostIp = IpUtil.getIp();
//            String url =System.getProperty("user.dir")+"\\el-xxljob-client\\xxl-job-client-demo\\src\\main\\resources\\doc\\mysql.json";
            String json = FindFileUtils.getJson(readPathJsonFileConfig.getPath()+"\\dataxJobDemo\\");
            //1.1将需要发送的数据备份到日志表中
            DateXExecuteParameter dateXExecuteParameter = new DateXExecuteParameter();
            dateXExecuteParameter.setCallback_url("/callBackService");
            dateXExecuteParameter.setClient_ip(hostIp+":8900");
            dateXExecuteParameter.setJobJson(json);
            dateXExecuteParameter.setJobId(23);
            dateXExecuteParameter.setProcessId(uuid);
            dateXExecuteParameter.setClient_service_name("任务");
            dateXExecuteParameter.setClient_task_name("");
    
            XxlJobLogger.log(json);
            //调用Datax服务
            String dataxResult1 = dataXExecuteServiceInterface.sayHello("hello");
    
            XxlJobLogger.log("running result"+dataxResult1);

        } catch (Exception e) {
            e.printStackTrace();
            logRecord.setLogType("F");
            logRecord.setMessage(e.getMessage().substring(0,100));
            return ReturnT.FAIL;
        } finally {
            logRecord.setDateTime(LocalDateTime.now(ZoneId.of("+8")));
            logMapperDao.insert(logRecord);
        }

        return ReturnT.SUCCESS;
    }

    @XxlJob("dataxPageJson")
    public ReturnT<String> dataxPageJson(String param){
        XxlJobLogger.log("param:"+param);

        return ReturnT.SUCCESS;
    }
}
