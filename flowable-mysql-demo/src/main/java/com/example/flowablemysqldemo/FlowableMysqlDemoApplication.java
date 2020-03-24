package com.example.flowablemysqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 配置mysql与flowable
 * 测试流程:
 * {@link com.example.flowablemysqldemo.controller.MyRestController}
 * 启动boot main
 * 按controller里的方式进行测试rest接口
 * 测试查看启动boot main
 * 1.获取受理人名kermit的task列表
 * curl http://localhost:8080/tasks?assignee=kermit
 * 2.开始一个流程
 * curl -X POST  http://localhost:8080/process
 * 3.再次查看
 * curl http://localhost:8080/tasks?assignee=kermit
 */
@SpringBootApplication(proxyBeanMethods = false)
public class FlowableMysqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableMysqlDemoApplication.class, args);
    }

}
