##执行器项目
 作用：负责接收“调度中心”的调度并执行；可直接部署执行器，也可以将执行器集成到现有业务项目中
 步骤一：maven依赖
 步骤二：执行器配置
 步骤三：执行器组件配置
 步骤四：部署执行器项目
 步骤五：执行器集群（可选）
##执行器API 
 API服务位置：com.xxl.job.core.biz.ExecutorBiz
 API服务请求参考代码：com.xxl.job.executorbiz.ExecutorBizTest
 1.心跳检测
 2.忙碌检测
 3.触发任务
 4.终止任务
 5.查看执行日志
 
 #项目结构：
 --java
   --com.xxl-job-executor
     --controller  API接口
     --core.config 核心配置文件
     --domain      实体类
     --mapper      mybatis接口
     --service     
       --impl      逻辑处理
       --jobhandler xxl-job执行器
       --rpc       接口远程调用
 --resources 配置文件
 ##开发规范
   参考阿里巴巴开发手册
       
     
     
 
 