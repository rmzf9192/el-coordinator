package com.xxl.job.executor.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * (DataxCallbackRecord)实体类
 *
 * @author roman,zhang
 * @since 2020-05-07
 */
@Data
public class LogRecord implements Serializable {
    private static final long serialVersionUID = -73901709675854514L;
    
    private Integer id;

    private Integer jobId;
    /**
    * xxl-job发送的标识以及接收的标识
    */
    private String dataxId;
    /**
     * 标识是发送还是返回:1:发送，0：返回
     */
    private Integer sendOrReceive;
    /**
    * datax处理成功/失败
    */
    private Integer flag;
    /**
    * 记录处理失败的信息
    */
    private String callbackMsg;
    /**
     * 回调接口地址
     */
    private String callBackApi;

}