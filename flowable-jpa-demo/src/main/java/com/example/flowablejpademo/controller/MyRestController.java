package com.example.flowablejpademo.controller;

import com.example.flowablejpademo.bean.MyProcess;
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
 * 1.部署流程实例
 * curl http://localhost:9090/deploy?bpmnName=one-task-process -X POST
 * 2.开始一个流程.指定受理人参数
 * curl -X POST  http://localhost:9090/process?assignee=jbarrez
 * 3.查看person.id为1的人员的tasks
 * curl http://localhost:9090/tasks?assignee=1
 * 4.部署另一个实例
 * curl http://localhost:9090/deploy?bpmnName=test -X POST
 * 5.开始另一个流程 admin
 * curl -X POST  http://localhost:9090/process?assignee=admin
 */
@RestController
public class MyRestController {

    @Autowired
    private IMyService myService;

    @PostMapping("/deploy")
    public String deployBPMN(String bpmnName){
        return myService.deploymentBpmn20(bpmnName+".bpmn20.xml");
    }

    @PostMapping("/process")
    public void startProcessInstance(@RequestParam("assignee") String assignee){
        List<MyProcess> allDeployProcess = myService.getAllDeployProcess();
        //获取最新部署的流程 开始流程
        MyProcess myProcess = allDeployProcess.get(0);
        myService.startProcess(myProcess.getProcessKey(),assignee);
    }

    /**
     * TODO 文档暂未提供,但方法提供
     * @param processId
     */
    @DeleteMapping("/delProcess")
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
