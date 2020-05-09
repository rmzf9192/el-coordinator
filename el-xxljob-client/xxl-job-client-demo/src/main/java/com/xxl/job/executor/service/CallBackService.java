package com.xxl.job.executor.service;

import com.el.trubine.rpc.common.bean.DataxResult;

import java.util.List;

/**
 * @author Roman.Zhang
 * @date 2020/5/7
 * @description: 回调接口
 */
public interface CallBackService {


    String update(String msg);

    /**
     * 回调接口
     */
    DataxResult callback(DataxResult dataxResult);



}
