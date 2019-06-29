package com.jmelon.onlinecourse.model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Course {
    @Id
    Long id;
    String name;
    String description;
    int durationInMinutes;
    Long certificate;
    double priceInEuro;
    String since;
    List<Long> prerequisiteCertificates = new ArrayList<>();
    List<Long> prerequisiteCourses = new ArrayList<>();
    List<String> tags = new ArrayList<>();




    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public Long getCertificate() {
        return certificate;
    }

    public double getPriceInEuro() {
        return priceInEuro;
    }

    public String getSince() {
        return since;
    }

    public List<Long> getPrerequisiteCertificates() {
        return prerequisiteCertificates;
    }

    public List<Long> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public List<String> getTags() {
        return tags;
    }
}
