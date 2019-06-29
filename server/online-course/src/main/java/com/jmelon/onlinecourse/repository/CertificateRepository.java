package com.jmelon.onlinecourse.repository;

import com.jmelon.onlinecourse.model.Certificate;
import com.jmelon.onlinecourse.model.Course;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CertificateRepository extends Neo4jRepository<Course, Long> {
    @Query("MATCH (c:Certificate) return c")
    Collection<Certificate> getAll();

    @Query("MATCH (c:Certificate) WHERE c.id IN {certificates} return c;")
    Collection<Certificate> getCertificatesByRange(List<Long> certificates);
}

