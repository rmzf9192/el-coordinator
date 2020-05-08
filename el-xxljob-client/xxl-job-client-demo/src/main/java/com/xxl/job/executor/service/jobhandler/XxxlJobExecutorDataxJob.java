package com.xxl.job.executor.service.jobhandler;

import com.el.trubine.rpc.common.bean.DateXExecuteParameter;
import com.el.trubine.rpc.common.service.DataXExecuteServiceInterface;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import com.xxl.job.core.util.IpUtil;
import com.xxl.job.executor.core.config.XxlJobReaderDatabaseConfig;
import com.xxl.job.executor.core.config.XxlJobWriterDatabaseConfig;
import com.xxl.job.executor.domain.DataxCallbackRecord;
import com.xxl.job.executor.service.CallBackService;
import com.xxl.job.executor.utils.XxlJobCommand;
import com.xxl.job.executor.utils.XxlJobReceived;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;

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
    //读数据源信息
    private final XxlJobReaderDatabaseConfig xxlJobDatabaseConfig;
    //写数据源信息
    private final XxlJobWriterDatabaseConfig xxlJobWriterDatabaseConfig;

    @Autowired
    private CallBackService callBackService;

    @Autowired
    private DataXExecuteServiceInterface dataXExecute;

    @XxlJob("dataxJobDemo")
    public ReturnT<String> dataxJobDemo(String param) throws Exception {

        //规划：全局异常捕获，并记录到异常表中
        //首先：判断是否需要发送执行指令（暂不判断）
        //1.获取数据源信息
        XxlJobLogger.log(XxlJobCommand.flag+"");
        XxlJobLogger.log(xxlJobDatabaseConfig.toString());
        XxlJobLogger.log(xxlJobWriterDatabaseConfig.toString());
//        XxlJobLogger.log("XXL-JOB, Hello World."+param);
        //获取服务器ip,执行器id
        String hostIp = IpUtil.getIp();
        XxlJobLogger.log(hostIp);
        //1.1将需要发送的数据备份到日志表中
        DataxCallbackRecord dataxCallbackRecord = new DataxCallbackRecord();
        boolean callbackRecord = callBackService.insert(dataxCallbackRecord);
        FileChannel inChannel = FileChannel.open(Paths.get(System.getProperty("user.dir")+"\\el-xxljob-client\\xxl-job-client-demo\\src\\main\\resources\\doc\\mysql.json"), StandardOpenOption.READ);

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        StringBuilder stringBuilder = new StringBuilder();
        while(inChannel.read(allocate)!=-1){
            allocate.flip();
            stringBuilder.append(new String(allocate.array(),0,allocate.limit()));
            System.out.println(new String(allocate.array(),0,allocate.limit()));
            //清空缓冲区
            allocate.clear();
        }
        XxlJobLogger.log(stringBuilder.toString());
        //2.设置同步属性（是datax的属性，暂时不设）

        //3.请求el-data-turbine,传递数据源及回调接口地址
//        TimeUnit.SECONDS.sleep(5000);
        var dateXExecuteParameter = new DateXExecuteParameter();
//        dataXExecute.dataXExecute(dateXExecuteParameter);
        //4.监测回调接口是否被调用
        XxlJobLogger.log(new XxlJobReceived().getReceived()+"");
        XxlJobCommand.flag = false;
        return ReturnT.SUCCESS;
    }
}
