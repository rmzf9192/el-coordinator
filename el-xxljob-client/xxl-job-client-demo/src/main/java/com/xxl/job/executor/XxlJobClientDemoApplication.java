package com.xxl.job.executor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/***
 * @author  niuchen20200427
 * **/
//@MapperScan("com.xxl.job.executor.mapper")
@SpringBootApplication
public class XxlJobClientDemoApplication {

	public static void main(String[] args) {
        SpringApplication.run(XxlJobClientDemoApplication.class, args);
	}
}