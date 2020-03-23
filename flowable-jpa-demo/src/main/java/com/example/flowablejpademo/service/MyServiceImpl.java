package com.example.flowablejpademo.service;

import com.example.flowablejpademo.bean.Person;
import com.example.flowablejpademo.dao.PersonRepository;
import org.apache.commons.lang3.time.DateUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author ukyo
 */
@Service
@Transactional
public class MyServiceImpl implements IMyService{

    /**
     *     操作流程运行时
     */
    @Autowired
    private RuntimeService runtimeService;

    /**
     *     操作任务
     */
    @Autowired
    private TaskService taskService;

    /**
     *     操作流程定义
     */
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private PersonRepository personRepository;

    /**
     * 部署xml
     * @param xmlFileName
     * @return
     */
    @Override
    public String deploymentBpmn20(String xmlFileName) {
        //processEngine启动资源服务 对配置xml文件进行获取及部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(xmlFileName)
                .deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .deploymentId(deployment.getId())
                .singleResult();
        String processName = processDefinition.getName();
        System.out.println("Found process definition : " + processDefinition.getName());
        String processKey = processDefinition.getKey();
        System.out.println("Process Key is:"+processKey);
        String processId = processDefinition.getId();
        System.out.println("Process Id is:"+processId);
        return processKey;
    }

    /**
     * 开始流程实例
     */
    @Override
    public void startProcess(String processKey,String assignee) {
        Person person = personRepository.findByUsername(assignee);
        System.out.println("get person:"+person.toString());
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("person",person);
        runtimeService.startProcessInstanceByKey(processKey,variables);
    }

    /**
     * TODO 500 No process instance found for id
     * @param processId
     * @param deleteReason
     */
    @Override
    @Transactional
    public void deleteProcessInstance(String processId, String deleteReason) {
        runtimeService.deleteProcessInstance(processId,deleteReason);
    }


    /**
     * 受理方获取任务list
     * @param assignee
     * @return
     */
    @Override
    public List<Task> getTasks(String assignee) {
        return taskService.createTaskQuery().taskAssignee(assignee).list();
    }

    @Override
    public void createDemoUsers(){
        if(personRepository.findAll().size()==0){
            personRepository.save(new Person("jbarrez", "Joram", "Barrez", new Date()));
            personRepository.save(new Person("trademakers", "Tijs", "Rademakers", new Date()));
        }

    }

}