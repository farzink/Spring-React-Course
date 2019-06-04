package com.jmelon.onlinecourse.repository;

import com.jmelon.onlinecourse.model.Todo;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TodoRepository extends Neo4jRepository<Todo, Long> {
    @Query("MATCH (t:Todo) return t")
    Collection<Todo> getAll();
}
