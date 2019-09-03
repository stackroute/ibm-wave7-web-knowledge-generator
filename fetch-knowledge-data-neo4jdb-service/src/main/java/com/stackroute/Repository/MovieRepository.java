package com.stackroute.Repository;

import com.stackroute.model.Actor;
import com.stackroute.model.Movie;
import com.stackroute.model.Node;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {
    @Query("MATCH (m)-[r]-(connected) WHERE {node} in labels(m) AND type(r) = {relation} RETURN connected as name")
    Collection<Node> findData(@Param("node") String node, @Param("relation") String relation);

}
