package com.xxl.job.executor.service.jobhandler;

import com.el.trubine.rpc.common.bean.DataxResult;
import com.el.trubine.rpc.common.bean.DateXExecuteParameter;
import com.el.trubine.rpc.common.service.DataXExecuteServiceInterface;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.IpUtil;
import com.xxl.job.executor.core.config.XxlJobReaderDatabaseConfig;
import com.xxl.job.executor.core.config.XxlJobWriterDatabaseConfig;
import com.xxl.job.executor.domain.LogRecord;
import com.xxl.job.executor.mapper.LogMapperDao;
import com.xxl.job.executor.utils.FindFileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private LogMapperDao logMapperDao;
//    @Autowired
//    private XxlJobInfoDao xxlJobInfoDao;
    @Autowired
    private DataXExecuteServiceInterface dataXExecuteServiceInterface;

    /**
     *
     * @param param 必须保持与任务ID一致
     * @return
     * @throws Exception
     */
    @XxlJob("dataxJobDemo")
    @Transactional
    public ReturnT<String> dataxJobDemo(String param) throws Exception {
        //是否向datax发送数据的标识
        boolean send = true;
//        LogRecord logRecord1 = new LogRecord();
        try {
            //通过日志记录该任务是否可以调用Datax:有记录不能调用datax,没有记录才可以调用
            List<LogRecord> byJobId = logMapperDao.findByJobId(Integer.valueOf(param));
            if(byJobId.size()>0){
                send =false;
            }

//            if(send){
            LogRecord logRecord = new LogRecord();
            logRecord.setJobId(Integer.valueOf(param));
            int insert = logMapperDao.insert(logRecord);
            String hostIp = IpUtil.getIp();
            String url =System.getProperty("user.dir")+"\\el-xxljob-client\\xxl-job-client-demo\\src\\main\\resources\\doc\\mysql.json";
            String json = FindFileUtils.getJson(url);
            //1.1将需要发送的数据备份到日志表中
            DataxResult dataxResult = new DataxResult();
            DateXExecuteParameter dateXExecuteParameter = new DateXExecuteParameter();
            dateXExecuteParameter.setCallback_url("/callBackService");
            dateXExecuteParameter.setClient_ip(hostIp+":8900");
            dateXExecuteParameter.setJobJson(json);
            dateXExecuteParameter.setJobId(23);
            dateXExecuteParameter.setProcessId(UUID.randomUUID().toString().replace("-",""));
            dateXExecuteParameter.setClient_service_name("任务");
            dateXExecuteParameter.setClient_task_name("");
    
            XxlJobLogger.log(json);
            //调用Datax服务
            String dataxResult1 = dataXExecuteServiceInterface.sayHello("hello");
    
            if(dataxResult1!=null){
                logMapperDao.updateByJobId(Integer.valueOf(param));
            }
    
            XxlJobLogger.log("running result"+dataxResult1);
            //3.请求el-data-turbine,传递数据源及回调接口地址
          /*  }else{
                XxlJobLogger.log("no send data to EL-Data-Turbine");
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

        return ReturnT.SUCCESS;
    }
}
