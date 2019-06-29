package com.jmelon.onlinecourse.model;

import org.springframework.data.neo4j.annotation.QueryResult;

@QueryResult
public class PersonCertificate {
    private Person person;
    private Certificate certificate;

    public Person getPerson() {
        return person;
    }

    public Certificate getCertificate() {
        return certificate;
    }
}
