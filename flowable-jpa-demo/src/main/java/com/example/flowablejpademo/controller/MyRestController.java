package com.example.flowablejpademo.controller;

import com.example.flowablejpademo.bean.TaskRepresentation;
import com.example.flowablejpademo.service.IMyService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ukyo
 * 按照flowable官网文档建立控制层
 * 比文档多一个deploy方法(如果部署不同的bpmn2.0文件请更换方法内名称或配置到properties Value读取)
 *
 * 测试查看启动boot main
 * 1.获取受理人名kermit的task列表
 * curl http://localhost:8080/tasks?assignee=kermit
 * 2.开始一个流程
 * curl -X POST  http://localhost:8080/process
 * 3.再次查看
 * curl http://localhost:8080/tasks?assignee=kermit
 *
 */
@RestController
public class MyRestController {

    @Autowired
    private IMyService myService;

    @PostMapping("/deploy")
    public String deployBPMN(){
        return myService.deploymentBpmn20("one-task-process.bpmn20.xml");
    }

    @PostMapping("/process")
    public void startProcessInstance(@RequestParam("assignee") String assignee){
        myService.startProcess("oneTaskProcess",assignee);
    }


    /**
     * TODO 文档暂未提供,但方法提供
     * @param processId
     */
    @DeleteMapping("delProcess")
    public void deleteProcessInstance(@RequestParam String processId){
        myService.deleteProcessInstance(processId,"test delete");
    }

    @GetMapping("/tasks")
    public List<TaskRepresentation> getTasks(@RequestParam("assignee") String assignee){
        List<Task> tasks = myService.getTasks(assignee);
        System.out.println("tasks:"+tasks);
        List<TaskRepresentation> dtos = new ArrayList<>();
        for(Task task:tasks){
            dtos.add(new TaskRepresentation(task.getId(),task.getName()));
        }
        return dtos;
    }

}
