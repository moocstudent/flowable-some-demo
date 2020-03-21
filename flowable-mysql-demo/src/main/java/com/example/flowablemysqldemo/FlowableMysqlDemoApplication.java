package com.example.flowablemysqldemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 配置mysql与flowable
 */
@SpringBootApplication(proxyBeanMethods = false)
public class FlowableMysqlDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableMysqlDemoApplication.class, args);
    }

}
