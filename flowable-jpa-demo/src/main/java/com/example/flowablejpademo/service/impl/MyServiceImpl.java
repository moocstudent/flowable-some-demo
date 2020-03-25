package com.example.flowablejpademo.service.impl;

import com.example.flowablejpademo.bean.MyProcess;
import com.example.flowablejpademo.bean.Person;
import com.example.flowablejpademo.repository.MyProcessRepository;
import com.example.flowablejpademo.repository.PersonRepository;
import com.example.flowablejpademo.service.IMyService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.hibernate.dialect.pagination.LimitHelper;
import org.hibernate.engine.spi.RowSelection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author ukyo
 */
@Service
@Transactional
public class MyServiceImpl implements IMyService {

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

    @Autowired
    private MyProcessRepository myProcessRepository;

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
        MyProcess mp = new MyProcess(processId,processName,processKey);
        MyProcess save = myProcessRepository.save(mp);
        System.out.println("my process saved:"+save);
        return processKey;
    }

    /**
     * 开始流程实例 传入流程参数key value
     */
    @Override
    public String startProcess(String processKey,String username) {
        List<Person> person = personRepository.findByUsername(username);
        System.out.println("person:"+person);
        HashMap<String, Object> variables = new HashMap<>();
        variables.put("person",person.get(0));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processKey, variables);
        String processDefinitionId = processInstance.getProcessDefinitionId();
        return processDefinitionId;
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
     * @param username
     * @return
     */
    @Override
    public List<Task> getTasks(String username) {
        return taskService.createTaskQuery().taskAssignee(username).list();
    }

    @Override
    public List<MyProcess> getAllDeployProcess(){
        List<MyProcess> allByCreateTimeDesc = myProcessRepository.findAllByOrderByCreateTimeDesc();
        allByCreateTimeDesc.forEach(c-> System.out.println("myProcess in db:"+c));
        return allByCreateTimeDesc;
    }

}