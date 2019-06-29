package com.jmelon.onlinecourse.model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Certificate {
    @Id
    Long id;
    String name;
    int durationInMinutes;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }
}
