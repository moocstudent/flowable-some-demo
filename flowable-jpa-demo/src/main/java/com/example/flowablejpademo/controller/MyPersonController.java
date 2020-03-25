package com.example.flowablejpademo.controller;

import com.example.flowablejpademo.bean.Person;
import com.example.flowablejpademo.service.IMyPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/person")
public class MyPersonController
{
    @Autowired
    private IMyPersonService myPersonService;

    @PostMapping("/onePerson")
    public Map<String,Object> addOnePerson(Person person){
        Map<String,Object> result = new HashMap<>();
        Person p = myPersonService.addPerson(person);
        if(p!=null){
            result.put("success",1);
            result.put("msg","save successful");
            result.put("personId",p.getId());
            return result;
        }else{
            result.put("success",0);
            result.put("msg","save failed");
            return result;
        }
    }
}
