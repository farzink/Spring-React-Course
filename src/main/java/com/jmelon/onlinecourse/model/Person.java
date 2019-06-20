package com.jmelon.onlinecourse.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import java.util.List;

public class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    private String birthday;
    private List<Long> achievedCertificates;
    private List<Long> visitedCourses;

    public Person() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public List<Long> getAchievedCertificates() {
        return achievedCertificates;
    }

    public void setAchievedCertificates(List<Long> achievedCertificates) {
        this.achievedCertificates = achievedCertificates;
    }

    public List<Long> getVisitedCourses() {
        return visitedCourses;
    }

    public void setVisitedCourses(List<Long> visitedCourses) {
        this.visitedCourses = visitedCourses;
    }








}
