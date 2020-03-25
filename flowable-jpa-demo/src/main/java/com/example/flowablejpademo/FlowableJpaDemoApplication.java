package com.example.flowablejpademo;

import com.example.flowablejpademo.service.IMyService;
import com.example.flowablejpademo.service.impl.MyServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * 测试查看启动boot main
 *
 * bpmn20.xml中:
 *  <userTask id="theTask" name="my task" flowable:assignee="${person.id}"/>
 * person.id发挥了jpa功效
 *
 * 查看actuator端点信息 curl http://localhost:8080/actuator/flowable
 */
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication(proxyBeanMethods = false)
public class FlowableJpaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlowableJpaDemoApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner init(final IMyService myService) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... strings) throws Exception {
//                System.out.println("start createDemoUsers");
//                myService.createDemoUsers();
//            }
//        };
//    }
}
