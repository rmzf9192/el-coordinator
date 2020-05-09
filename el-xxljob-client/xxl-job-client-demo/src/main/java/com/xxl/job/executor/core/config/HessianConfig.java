package com.xxl.job.executor.core.config;

import com.el.trubine.rpc.common.service.DataXExecuteServiceInterface;
import com.xxl.job.executor.service.CallBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * @author Roman.Zhang
 * @date 2020/5/7
 * @description:
 */
@Configuration
public class HessianConfig {
    @Autowired
    CallBackService callBackService;
    //Hessian客户端
    @Bean
    public HessianProxyFactoryBean helloClient() {
        HessianProxyFactoryBean factoryBean = new HessianProxyFactoryBean();
        factoryBean.setServiceUrl("http://127.0.0.1:7881/dataXExecuteService");
        factoryBean.setServiceInterface(DataXExecuteServiceInterface.class);
        return factoryBean;
    }

    //Hessian服务端:发布服务
    @Bean(name="/callBackService")
    public HessianServiceExporter accountService() {
        HessianServiceExporter exporter = new HessianServiceExporter();
        exporter.setService(callBackService);
        exporter.setServiceInterface(CallBackService.class);
        return exporter;
    }


}
