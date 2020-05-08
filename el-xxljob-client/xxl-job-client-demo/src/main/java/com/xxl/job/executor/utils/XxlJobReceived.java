package com.xxl.job.executor.utils;

/**
 * @author Roman.Zhang
 * @date 2020/5/8
 * @description: 接收者
 */
public class XxlJobReceived {

    public boolean getReceived(){
        return XxlJobCommand.flag;
    }
}
