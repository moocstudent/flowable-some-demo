package com.example.flowablemysqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 配置mysql与flowable
 * 测试流程:
 * {@link com.example.flowablemysqldemo.controller.MyRestController}
 * 启动boot main
 * 按controller里的方式进行测试rest接口
 */
@SpringBootApplication(proxyBeanMethods = false)
public class FlowableMysqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableMysqlDemoApplication.class, args);
    }

}
