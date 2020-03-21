package com.example.flowablemysqldemo.controller;

import com.example.flowablemysqldemo.service.MyService;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MyRestController {

    @Autowired
    private MyService myService;

    @PostMapping("/deploy")
    public String deployBPMN(){
        return myService.deploymentBPMN20("one-task-process.bpmn20.xml");
    }

    @PostMapping("/process")
    public void startProcessInstance(){
        myService.startProcess("oneTaskProcess");
    }

    @GetMapping("/tasks")
    public List<TaskRepresentation> getTasks(@RequestParam String assignee){
        List<Task> tasks = myService.getTasks(assignee);
        List<TaskRepresentation> dtos = new ArrayList<>();
        for(Task task:tasks){
            dtos.add(new TaskRepresentation(task.getId(),task.getName()));
        }
        return dtos;
    }

    //任务陈述
    static class TaskRepresentation{
        private String id;
        private String name;

        public TaskRepresentation(String id,String name){
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }


}
