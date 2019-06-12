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
    private List<Long> prerequisiteCertificates;
    private List<Long> prerequisiteCourses;
    private List<String> tags;

    private Course(){
            //  Empty constructor required as of Neo4j API 2.0.5
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationInMinutes(float durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public void setCertificate(boolean certificate) {
        this.certificate = certificate;
    }

    public void setPriceInEuro(float priceInEuro) {
        this.priceInEuro = priceInEuro;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public void setPrerequisiteCertificates(List<Long> prerequisiteCertificates) {
        this.prerequisiteCertificates = prerequisiteCertificates;
    }

    public void setPrerequisiteCourses(List<Long> prerequisiteCourses) {
        this.prerequisiteCourses = prerequisiteCourses;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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
