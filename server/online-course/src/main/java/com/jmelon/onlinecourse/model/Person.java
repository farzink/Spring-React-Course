package com.jmelon.onlinecourse.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String firstname;
    private String lastname;
    private String birthday;
    private List<Long> achievedCertificates;
    private List<Long> visitedCourses;

    public Person() {
        this.achievedCertificates = new ArrayList<>();
        this.visitedCourses = new ArrayList<>();
    }

    public Person(Long id, String firstname, String lastname, String birthday, List<Long> achievedCertificates, List<Long> visitedCourses) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
        this.achievedCertificates = achievedCertificates;
        this.visitedCourses = visitedCourses;
    }

    public Person(String firstname, String lastname, String birthday) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthday = birthday;
    }

    public Long getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public List<Long> getAchievedCertificates() {
        return achievedCertificates;
    }

    public List<Long> getVisitedCourses() {
        return visitedCourses;
    }
}
