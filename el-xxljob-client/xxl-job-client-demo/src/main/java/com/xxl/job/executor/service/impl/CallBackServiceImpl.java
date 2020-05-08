package com.xxl.job.executor.service.impl;

import com.xxl.job.executor.domain.DataxCallbackRecord;
import com.xxl.job.executor.mapper.DataxCallbackRecordDao;
import com.xxl.job.executor.service.CallBackService;
import com.xxl.job.executor.service.Observer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @author Roman.Zhang
 * @date 2020/5/7
 * @description:
 */
@Service("callBackService")
@Slf4j
public class CallBackServiceImpl implements CallBackService, Observer {
    @Resource
    private DataxCallbackRecordDao dataxCallbackRecordDao;
    //发送的数据存于该集合中，返回的数据将被删除
    private ArrayList<DataxCallbackRecord> dataxRecords;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DataxCallbackRecord queryById(Integer id) {
        return this.dataxCallbackRecordDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param dataxCallbackRecord 实例对象
     * @return 实例对象
     */
    @Override
    public boolean insert(DataxCallbackRecord dataxCallbackRecord) {
        int insert = this.dataxCallbackRecordDao.insert(dataxCallbackRecord);
        return insert > 0 ? true:false;
    }

    @Override
    public String update(String msg) {
        System.out.println("xxljob服务端成功");
        return "hello "+msg;
    }



    @Override
    public boolean addDataXRecord(DataxCallbackRecord dataxCallbackRecord) {
        return dataxRecords.add(dataxCallbackRecord);
    }

    @Override
    public boolean removeDataXRecord(DataxCallbackRecord dataxCallbackRecord) {
        return dataxRecords.remove(dataxCallbackRecord);
    }
}
