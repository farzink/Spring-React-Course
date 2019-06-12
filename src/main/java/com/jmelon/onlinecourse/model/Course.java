package com.jmelon.onlinecourse.model;


import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.text.SimpleDateFormat;
import java.util.List;

@NodeEntity
public class Course {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private float durationInMinutes;
    private boolean certificate;
    private float priceInEuro;
    private String since;
    private List<String> prerequisiteCertificates;
    private List<String> prerequisiteCourses;
    private List<String> tags;

    private Course(){
            //  Empty constructor required as of Neo4j API 2.0.5
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescrption() {
        return description;
    }

    public float getDurationInMinutes() {
        return durationInMinutes;
    }

    public boolean isCertificate() {
        return certificate;
    }

    public float getPriceInEuro() {
        return priceInEuro;
    }

    public String getSince() {
        return since;
    }

    public List<String> getPrerequisiteCertificates() {
        return prerequisiteCertificates;
    }

    public List<String> getPrerequisiteCourses() {
        return prerequisiteCourses;
    }

    public List<String> getTags() {
        return tags;
    }


}
