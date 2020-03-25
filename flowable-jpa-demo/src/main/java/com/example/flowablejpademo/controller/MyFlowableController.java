package com.example.flowablejpademo.controller;

import com.example.flowablejpademo.bean.MyProcess;
import com.example.flowablejpademo.bean.TaskRepresentation;
import com.example.flowablejpademo.service.IMyService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ukyo
 * 按照flowable官网文档建立控制层
 * 比文档多一个deploy方法(如果部署不同的bpmn2.0文件请更换方法内名称或配置到properties Value读取)
 *
 */
@RestController
public class MyFlowableController {

    @Autowired
    private IMyService myService;

    @PostMapping("/deploy")
    public String deployBPMN(String bpmnName){
        return myService.deploymentBpmn20(bpmnName+".bpmn20.xml");
    }

    @PostMapping("/process")
    public Map<String,Object> startProcessInstance(String processKey,String assignee){
        Map<String,Object> result = new HashMap<>();
        if(StringUtils.isEmpty(processKey)||StringUtils.isEmpty(assignee)){
            result.put("success",0);
            result.put("msg","processKey & assignee can't be null.");
            return result;
        }
        String processDefinitionId = myService.startProcess(processKey, assignee);
        if(!StringUtils.isEmpty(processDefinitionId)){
            result.put("success",1);
            result.put("msg","start process instance "+processKey+" ok.The assignee username is "+assignee);
            return result;
        }else{
            result.put("success",0);
            result.put("msg","start process instance "+processKey+" failed.Please checkout your variables and try again.");
            return result;
        }
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
