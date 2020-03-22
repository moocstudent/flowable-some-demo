package com.example.flowablejpademo.service;

import org.flowable.task.api.Task;

import java.util.List;

public interface IMyService
{

    public String deploymentBpmn20(String xmlFileName);

    public void startProcess(String processKey,String assignee);

    public void deleteProcessInstance(String processId, String deleteReason);

    public List<Task> getTasks(String assignee);

    public void createDemoUsers();


}
