package com.jmelon.onlinecourse.model;

import com.opencsv.bean.CsvBindByPosition;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Person {
    @Id
    @GeneratedValue
    @CsvBindByPosition(position = 0)
    private Long id;
    @CsvBindByPosition(position = 1)
    private String firstname;
    @CsvBindByPosition(position = 2)
    private String lastname;
    @CsvBindByPosition(position = 3)
    private String birthday;
    @CsvBindByPosition(position = 4)
    private List<Long> achievedCertificates = new ArrayList<>();
    @CsvBindByPosition(position = 5)
    private List<Long> visitedCourses = new ArrayList<>();
    @CsvBindByPosition(position = 6)
    private boolean isHidden = false;

    public boolean getIsHidden() {
        return isHidden;
    }

    public Person() {
        this.achievedCertificates = new ArrayList<>();
        this.visitedCourses = new ArrayList<>();
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
