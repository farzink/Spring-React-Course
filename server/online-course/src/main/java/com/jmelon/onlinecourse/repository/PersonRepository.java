package com.jmelon.onlinecourse.repository;

import com.jmelon.onlinecourse.model.Person;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    @Query("MATCH (t:Person) return t")
    Collection<Person> getAll();
}
