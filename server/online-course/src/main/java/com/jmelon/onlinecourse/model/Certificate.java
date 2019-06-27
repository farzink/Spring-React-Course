package com.jmelon.onlinecourse.model;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Certificate {
    @Id
    long id;
    String name;
    int durationInMinutes;
}
