package com.stackroute.repository;

import com.stackroute.domain.Movie;
import org.springframework.data.neo4j.annotation.Query;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.Collection;

@Repository
public interface MovieRepository extends Neo4jRepository<Movie, Long> {


    @Query("WITH :#{#movie} AS data\n" +
            "WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "MERGE (movie:Movie {title: data.Title})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "UNWIND split(data.Production, ',') AS production\n" +
            "MERGE (productionnew:Production {company: production})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "MATCH (movie:Movie {title: data.Title})\n" +
            "UNWIND split(data.Production, ',') AS production1\n" +
            "MATCH (production1new:Production {company: production1})\n" +
            "MERGE (movie)-[r:PRODUCED_BY]->(production1new)\n" +
            "MERGE (movie)<-[rb:PRODUCED]-(production1new)\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "UNWIND split(data.Director, ',') AS director\n" +
            "MERGE (directornew:Director {name: director})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "MATCH (movie:Movie {title: data.Title})\n" +
            "UNWIND split(data.Director, ',') AS director1\n" +
            "MATCH (director1new:Director {name: director1})\n" +
            "MERGE (movie)-[r:DIRECTED_BY]->(director1new)\n" +
            "MERGE (movie)<-[rb:DIRECTED]-(director1new)\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "UNWIND split(data.Writer, ',') AS writer\n" +
            "MERGE (writernew:Writer {name: writer})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "MATCH (movie:Movie {title: data.Title})\n" +
            "UNWIND split(data.Writer, ',') AS writer1\n" +
            "MATCH (writer1new:Writer {name: writer1})\n" +
            "MERGE (movie)-[r:WRITTEN_BY]->(writer1new)\n" +
            "MERGE (movie)<-[rb:WRITTEN]-(writer1new)\n"+
            "WITH data WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "UNWIND split(data.Actor, ',') AS actor \n" +
            "MERGE (actornew:Actor {name: actor})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "MATCH (movie:Movie {title: data.Title})\n" +
            "UNWIND split(data.Actor, ',') AS actor1\n" +
            "MATCH (actor1new:Actor {name: actor1})\n" +
            "MERGE (movie)-[r:HAS_ACTOR]->(actor1new)\n" +
            "MERGE (movie)<-[rb:ACTED_IN]-(actor1new)\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "MERGE (year:ReleasedYear {year: data.Year})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Actor='null' AND data.Director='null' AND data.Year='null') \n" +
            "MATCH (movie:Movie {title: data.Title})\n" +
            "MATCH (year:ReleasedYear {year: data.Year})\n" +
            "MERGE (movie)-[r:RELEASED_IN]->(year)\n" +
            "MERGE (movie)<-[rb:HAS_MOVIE]-(year)\n")
            public Collection<Movie> saveMovie(@Param("movie") Movie movie);
}


