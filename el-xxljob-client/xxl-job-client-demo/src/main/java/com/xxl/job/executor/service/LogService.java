package com.xxl.job.executor.service;

import com.xxl.job.executor.domain.LogRecord;

/**
 * @author Roman.Zhang
 * @date 2020/5/9
 * @description:
 */
public interface LogService {
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    LogRecord queryById(Integer id);

    /**
     * 新增数据
     *
     * @param dataxCallbackRecord 实例对象
     * @return 实例对象
     */
    boolean insert(LogRecord dataxCallbackRecord);
}
