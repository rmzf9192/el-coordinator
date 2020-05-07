package com.xxl.job.executor.mapper;

import com.xxl.job.executor.domain.DataxCallbackRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * (DataxCallbackRecord)表数据库访问层
 *
 * @author roman,zhang
 * @since 2020-05-07 15:00:37
 */
public interface DataxCallbackRecordDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Select("SELECT * FROM datax-callback-record WHERE id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "dataxId", column = "datax-id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "callbackMsg", column = "callback-msg")
    })
    DataxCallbackRecord queryById(Integer id);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dataxCallbackRecord 实例对象
     * @return 对象列表
     */
    @Select("SELECT * FROM datax_callback_record")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "dataxId", column = "datax_id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "callbackMsg", column = "callback_msg")
    })
    List<DataxCallbackRecord> queryAll(DataxCallbackRecord dataxCallbackRecord);

    /**
     * 新增数据
     *
     * @param dataxCallbackRecord 实例对象
     * @return 影响行数
     */
    @Insert("INSERT INTO datax_callback_record(datax_id,flag,callback_msg) VALUES(#{dataxId}, #{flag}, #{callbackMsg})")
    int insert(DataxCallbackRecord dataxCallbackRecord);
}