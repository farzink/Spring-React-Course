package com.jmelon.onlinecourse.repository;

import com.jmelon.onlinecourse.model.Course;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CourseRepository extends Neo4jRepository<Course, Long> {
    /*@Query("MATCH (t:Course) return t")
    Collection<Course> getAll();*/


}
