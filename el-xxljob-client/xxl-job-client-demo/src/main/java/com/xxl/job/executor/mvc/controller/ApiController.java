package com.xxl.job.executor.mvc.controller;

import com.xxl.job.executor.domain.DataxCallbackRecord;
import com.xxl.job.executor.service.CallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author Roman.Zhang
 * @date 2020/5/7
 * @description: 回调接口：用于Datax服务调用，以便于给xxl-job回传处理信息。
 *                        目前考虑，存于数据库中。需要配置专属记录的数据源（该数据源记录执行的任务，以及任务的执行情况）
 */
@RestController
public class ApiController {
    @Autowired
    private CallBackService callBackService;

    @RequestMapping(value = "/callback")
    public String callBack(@RequestParam("dataxId") String dataxId,@RequestParam("flag") Integer flag,
                           @RequestParam("callbackMsg") String callbackMsg ){
        if(!Objects.equals(dataxId,null)){
            DataxCallbackRecord dataxCallbackRecord = new DataxCallbackRecord();
            dataxCallbackRecord.setCallbackMsg(callbackMsg);
            dataxCallbackRecord.setDataxId(dataxId);
            dataxCallbackRecord.setFlag(flag);
            callBackService.insert(dataxCallbackRecord);
        }
        return "xxj-job received";
    }

    @RequestMapping("/test")
    public String test(){
        return "xxj-receviced";
    }

    @RequestMapping("/")
    public String test1(){
        return "xxj-receviced1";
    }
}
