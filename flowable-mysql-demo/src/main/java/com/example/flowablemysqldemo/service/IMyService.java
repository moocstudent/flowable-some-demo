package com.example.flowablemysqldemo.service;

import org.flowable.task.api.Task;

import java.util.List;

public interface IMyService
{

    public String deploymentBPMN20(String xmlFileName);

    public void startProcess(String processKey);

    public void deleteProcessInstance(String processId,String deleteReason);

    public List<Task> getTasks(String assignee);


}
