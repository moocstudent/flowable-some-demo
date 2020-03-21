package com.example.flowabledemo;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * console测试Flowable
 * {@link FlowableMainTest}
 */
@SpringBootApplication(proxyBeanMethods = false)
public class FlowableDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableDemoApplication.class, args);
    }

}
