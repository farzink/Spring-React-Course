package com.jmelon.onlinecourse.model;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

@RelationshipEntity(type = "ENROLLED")
public class Enrolment {
    @Id
    private Long id;
    @StartNode
    private Person person;
    @EndNode
    private Course course;
    private int progress;
    private String enrolmentDate;

    public String getEnrolmentDate() {
        return enrolmentDate;
    }

    public Enrolment() {
    }

    public Enrolment(Long id, Person person, Course course, int progress) {
        this.id = id;
        this.person = person;
        this.course = course;
        this.progress = progress;
    }

    public Long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Course getCourse() {
        return course;
    }

    public int getProgress() {
        return progress;
    }
}
