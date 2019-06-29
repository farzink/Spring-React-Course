package com.jmelon.onlinecourse.service;


import com.jmelon.onlinecourse.model.Person;

import com.jmelon.onlinecourse.model.PersonCertificate;
import com.jmelon.onlinecourse.model.ServiceResultModel;
import com.jmelon.onlinecourse.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class PersonService extends BaseService {

    @Autowired
    PersonRepository personRepository;

    public Collection<Person> getAll() {
        return personRepository.getAll();
    }

    public void create(Person person) {
        personRepository.save(person);
    }

    public Person updatePersonVisibilityStatus(Long personId, boolean visibilityStatus) {
        try {
            return personRepository.updatePersonVisibilityStatus(personId, visibilityStatus);
        } catch (Exception ex) {
            return null;
        }
    }

    public Person getProfileById(Long id) {
        try {
            return personRepository.getProfileById(id);
        } catch (Exception ex) {
            return null;
        }
    }

    public ServiceResultModel<Boolean> addToVisitedCourses(Long personId, Long courseId) {
        var result = this.createResultModel();
        try {
            Person person = personRepository.getProfileById(personId);
            if (person == null) {
                throw new Exception("no such a person");
            }
            var currentVisited = person.getVisitedCourses().stream().collect(toList());
            currentVisited.add(courseId);
            var visitedCourses = currentVisited
                    .stream().distinct().collect(Collectors.toCollection(ArrayList::new));
            personRepository.addToVisitedCourses(personId, visitedCourses);
            result.setData(true);
            return result;
        } catch (Exception ex) {
            //todo: log the exception
            result.failWithDataAndMessage(false, ex.getMessage());
            return result;
        }
    }

    public Person addCetificate(Long personId, Long certificateId) {
        try {
            var person = getProfileById(personId);
            if (person == null) {
                throw new Exception();
            }
            var currentCertificates = person.getAchievedCertificates().stream().collect(toList());
            currentCertificates.add(certificateId);
            var certificates = currentCertificates
                    .stream().distinct().collect(Collectors.toCollection(ArrayList::new));
            var result= personRepository.updateCertificates(personId, certificates);
            return result;
        } catch (Exception ex) {
            return null;
        }
    }

    public ServiceResultModel<Collection<Person>> findPeopleWithCertainCertificate(Long certificateId) {
        var result = this.createResultModel();
        try {
            result.setData(personRepository.findPeopleWithCertainCertificate(certificateId));
            return result;
        } catch (Exception ex) {
            //todo: log the exception
            result.failWithDataAndMessage(null, ex.getMessage());
            return result;
        }
    }


}
