package com.example.flowablejpademo.service;

import com.example.flowablejpademo.bean.Person;

import java.util.List;

public interface IMyPersonService {

    Person addPerson(Person person);

    List<Person> getPersonByUsername(String username);


}
