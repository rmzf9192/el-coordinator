package com.xxl.job.executor.service;

import com.xxl.job.executor.domain.DataxCallbackRecord;

import java.util.List;

/**
 * @author Roman.Zhang
 * @date 2020/5/7
 * @description: 回调接口
 */
public interface CallBackService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DataxCallbackRecord queryById(Integer id);


    /**
     * 新增数据
     *
     * @param dataxCallbackRecord 实例对象
     * @return 实例对象
     */
    boolean insert(DataxCallbackRecord dataxCallbackRecord);

    String update(String msg);



}
