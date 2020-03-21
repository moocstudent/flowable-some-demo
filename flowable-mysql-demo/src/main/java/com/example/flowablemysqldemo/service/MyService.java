package com.example.flowablemysqldemo.service;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MyService implements IMyService{

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public String deploymentBPMN20(String xmlFileName) {
        Deployment deploy = repositoryService.createDeployment().addClasspathResource(xmlFileName).deploy();
        String parentDeploymentId = deploy.getParentDeploymentId();
        return parentDeploymentId;
    }

    /**
     * 开始流程实例
     */
    @Override
    @Transactional
    public void startProcess(String processKey) {
        runtimeService.startProcessInstanceByKey(processKey);
    }

    /**
     * 受理方获取任务list
     * @param assignee
     * @return
     */
    @Override
    @Transactional
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

}