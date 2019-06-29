package com.jmelon.onlinecourse.repository;

import com.jmelon.onlinecourse.model.Person;
import com.jmelon.onlinecourse.model.PersonCertificate;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Repository
public interface PersonRepository extends Neo4jRepository<Person, Long> {
    @Query("MATCH (p:Person) return p;")
    Collection<Person> getAll();

    @Query("MATCH (p:Person) where id(p) = {personId} set p.isHidden = {status} return p;")
    Person updatePersonVisibilityStatus(Long personId, boolean status);


    @Query("MATCH (p:Person) WHERE id(p) = {personId} return p;")
    Person getProfileById(Long personId);

    @Query("MATCH (p:Person) WHERE id(p) = {personId} SET p.visitedCourses = {visitedCourses}")
    void addToVisitedCourses(Long personId, List<Long> visitedCourses);


    @Query("MATCH (p:Person) WHERE id(p) = {personId} SET p.achievedCertificates = {certificates} " +
            " return p as person")
    Person updateCertificates(Long personId, List<Long> certificates);

    @Query("MATCH (p:Person) WHERE {certificateId} IN p.achievedCertificates AND p.isHidden=false return p")
    Collection<Person> findPeopleWithCertainCertificate(Long certificateId);


}


