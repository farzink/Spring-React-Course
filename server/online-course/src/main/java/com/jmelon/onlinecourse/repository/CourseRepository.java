package com.jmelon.onlinecourse.repository;

import com.jmelon.onlinecourse.model.Course;
import com.jmelon.onlinecourse.model.EnrolledCourseInfo;
import com.jmelon.onlinecourse.model.Enrolment;
import com.jmelon.onlinecourse.model.Todo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CourseRepository extends Neo4jRepository<Course, Long> {
    @Query("MATCH (t:Course) return t")
    Collection<Course> getAll();

    @Query("MATCH (p:Person)-[e:ENROLLED]->(c:Course) WHERE id(p) = {id} RETURN c as course, " +
            "head(collect(e)).enrolmentDate as enrolmentDate, head(collect(e)).progress as progress")
    Collection<EnrolledCourseInfo> getCoursesByPersonId(Long id);

    @Query("MATCH (p:Person), (c:Course) WHERE id(p) = {personId} AND id(c) = {courseId} " +
            "MERGE (p)-[e:ENROLLED {progress: 0, enrolmentDate: {enrolmentDate}}]->(c) " +
            "return p as user, collect(e), c as course;")
    Enrolment enrollByCourseAndPersonId(Long personId, Long courseId, String enrolmentDate);


    @Query("MATCH (p:Person)-[e:ENROLLED]->(c:Course) where id(p) = {personId} AND id(c) = {courseId} delete e")
    void leaveByCourseAndPersonId(Long personId, Long courseId);

    @Query("MATCH (p:Person)-[e:ENROLLED]->(c:Course) WHERE id(p) = {personId} AND id(c) = {courseId}" +
            "return c as course, head(collect(e)).enrolmentDate as enrolmentDate, head(collect(e)).progress as progress")
    EnrolledCourseInfo getEnrolledCourseInfoByPersonId(Long personId, Long courseId);

}
