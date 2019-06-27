package com.jmelon.onlinecourse.model;

import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;
import org.springframework.data.neo4j.annotation.QueryResult;

import java.util.Set;

@QueryResult
public class EnrolledCourseInfo {
    Course course;
    String enrolmentDate;
    int progress;

    public Course getCourse() {
        return course;
    }

    public String getEnrolmentDate() {
        return enrolmentDate;
    }

    public int getProgress() {
        return progress;
    }
}
