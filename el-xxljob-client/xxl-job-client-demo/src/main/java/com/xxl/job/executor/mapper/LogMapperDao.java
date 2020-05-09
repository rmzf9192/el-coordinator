package com.xxl.job.executor.mapper;

import com.xxl.job.executor.domain.LogRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * (DataxCallbackRecord)表数据库访问层
 *
 * @author roman,zhang
 * @since 2020-05-07 15:00:37
 */
public interface LogMapperDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Select("SELECT * FROM datax-callback-record WHERE id = #{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "dataxId", column = "datax_id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "callbackMsg", column = "callback_msg"),
            @Result(property = "jobId", column = "job_id")
    })
    LogRecord queryById(Integer id);


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
            @Result(property = "callbackMsg", column = "callback_msg"),
            @Result(property = "jobId", column = "job_id")
    })
    List<LogRecord> queryAll(LogRecord dataxCallbackRecord);

    /**
     * 新增数据
     *
     * @param dataxCallbackRecord 实例对象
     * @return 影响行数
     */
    @Insert("INSERT INTO datax_callback_record(datax_id,flag,callback_msg,job_id) VALUES(#{dataxId}, #{flag}, #{callbackMsg},#{jobId})")
    int insert(LogRecord dataxCallbackRecord);

    @Select("SELECT callback_msg,job_id FROM datax_callback_record WHERE job_id = #{jobId}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "dataxId", column = "datax_id"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "callbackMsg", column = "callback_msg"),
            @Result(property = "jobId", column = "job_id")
    })
    List<LogRecord> findByJobId(@Param("jobId") Integer jobId);

    @Update("UPDATE datax_callback_record SET job_id = 0 where job_id =#{jobId} ")
    int updateByJobId(@Param("jobId") Integer jobId);
}