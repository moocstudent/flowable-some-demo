package com.example.flowablejpademo;

import com.example.flowablejpademo.service.IMyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * TODO 未完全跑通
 * 针对jpa
 *
 * 查看actuator端点信息 curl http://localhost:8080/actuator/flowable
 */
@SpringBootApplication(proxyBeanMethods = false)
public class FlowableJpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableJpaDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner init(final IMyService myService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                System.out.println("start createDemoUsers");
                myService.createDemoUsers();
            }
        };
    }
}
