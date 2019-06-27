package com.jmelon.onlinecourse.service;


import com.jmelon.onlinecourse.model.Person;

import com.jmelon.onlinecourse.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public Collection<Person> getAll() {
        return personRepository.getAll();
    }

    public void create(Person person) {
        personRepository.save(person);
    }
}
