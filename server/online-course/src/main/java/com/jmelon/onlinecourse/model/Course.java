package com.jmelon.onlinecourse.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.GraphId;
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
    Certificate certificate;
    double priceInEuro;
    String since;
    List<Certificate> prerequisiteCertificates;
    List<Course> prerequisiteCourses;
    List<String> tags;
    public Course() {
        this.prerequisiteCertificates= new ArrayList<>();
        this.prerequisiteCourses = new ArrayList<>();
        this.tags = new ArrayList<>();
    }

    public Course(Long id, String name, String description, int durationInMinutes, Certificate certificate, double priceInEuro, String since, List<Certificate> prerequisiteCertificates, List<Course> prerequisiteCourses, List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationInMinutes = durationInMinutes;
        this.certificate = certificate;
        this.priceInEuro = priceInEuro;
        this.since = since;
        this.prerequisiteCertificates = prerequisiteCertificates;
        this.prerequisiteCourses = prerequisiteCourses;
        this.tags = tags;
    }

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

    public Certificate getCertificate() {
        return certificate;
    }

    public double getPriceInEuro() {
        return priceInEuro;
    }

    public String getSince() {
        return since;
    }

    public List<Certificate> getPrerequisiteCertificates() {
        return prerequisiteCertificates;
    }

    public List<Course> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public List<String> getTags() {
        return tags;
    }
}
