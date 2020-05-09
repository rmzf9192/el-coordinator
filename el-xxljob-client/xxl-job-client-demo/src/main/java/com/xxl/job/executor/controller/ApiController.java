package com.xxl.job.executor.controller;

import com.xxl.job.executor.domain.LogRecord;
import com.xxl.job.executor.service.CallBackService;
import io.swagger.annotations.*;
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
@RequestMapping("/api")
@Api(tags = "回调接口")
public class ApiController {
    @Autowired
    private CallBackService callBackService;

    @ApiOperation("回调接口-接收处理信息")
    @RequestMapping(value = "/callback",method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dataxId", value = "任务唯一标识",required = true),
            @ApiImplicitParam(name = "flag", value = "处理结果",  required = true),
            @ApiImplicitParam(name = "callbackMsg", value = "处理结果详情")
    })
    public String callBack(@RequestParam("dataxId") String dataxId,@RequestParam("flag") Integer flag,
                           @RequestParam("callbackMsg") String callbackMsg ){
        if(!Objects.equals(dataxId,null)){
            LogRecord dataxCallbackRecord = new LogRecord();
            dataxCallbackRecord.setCallbackMsg(callbackMsg);
            dataxCallbackRecord.setDataxId(dataxId);
            dataxCallbackRecord.setFlag(flag);
//            callBackService.insert(dataxCallbackRecord);
        }
        return "xxj-job received";
    }

   /* @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(){
        return "xxj-receviced";
    }

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String test1(){
        return "xxj-receviced1";
    }*/
}
