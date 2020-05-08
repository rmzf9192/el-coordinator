package com.xxl.job.executor.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Roman.Zhang
 * @date 2020/5/8
 * @description: Swagger配置类
 */
@Configuration
@EnableSwagger2 //启用Swagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//主要配置一下Swagger2文档网站的信息，例如网站的title，网站的描述，联系人的信息，使用的协议等等。
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xxl.job.executor.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    //配置在线文档的基本信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("springboot利用swagger构建api文档")
                .description("简单优雅的restfun风格，https://me.csdn.net/blog/miachen520")
                .termsOfServiceUrl("https://me.csdn.net/blog/miachen520")
                .version("1.0")
                .build();
    }


}
