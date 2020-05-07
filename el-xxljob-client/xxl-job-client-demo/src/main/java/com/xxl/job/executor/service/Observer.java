package com.xxl.job.executor.service;

import com.xxl.job.executor.domain.DataxCallbackRecord;

/**
 * @author Roman.Zhang
 * @date 2020/5/7
 * @description:
 */
public interface Observer {
    /**
     * 添加发送信息
     * @param dataxCallbackRecord
     * @return
     */
    boolean addDataXRecord(DataxCallbackRecord dataxCallbackRecord);

    /**
     * 删除发送信息
     * @param dataxCallbackRecord
     * @return
     */
    public boolean removeDataXRecord(DataxCallbackRecord dataxCallbackRecord);
}
