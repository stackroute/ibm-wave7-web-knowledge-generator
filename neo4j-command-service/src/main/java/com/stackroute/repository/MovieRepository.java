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
            "WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "MERGE (movie:Movie {title: data.Title})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "UNWIND split(data.ProductionHouse, ',') AS productionHouse\n" +
            "MERGE (varProductionHouse:ProductionHouse {company: productionHouse})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "MATCH (movie:Movie {title: data.Title})\n" +
            "UNWIND split(data.ProductionHouse, ',') AS matchedProductionHouse\n" +
            "MATCH (varMatchedProductionHouse:ProductionHouse {company: matchedProductionHouse})\n" +
            "MERGE (movie)-[r:PRODUCTION_BY]->(varMatchedProductionHouse)\n" +
            "MERGE (movie)<-[rb:PRODUCTION]-(varMatchedProductionHouse)\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "UNWIND split(data.Director, ',') AS director\n" +
            "MERGE (varDirector:Director {name: director})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "MATCH (movie:Movie {title: data.Title})\n" +
            "UNWIND split(data.Director, ',') AS matchedDirector\n" +
            "MATCH (varMatchedDirector:Director {name: matchedDirector})\n" +
            "MERGE (movie)-[r:DIRECTED_BY]->(varMatchedDirector)\n" +
            "MERGE (movie)<-[rb:DIRECTED]-(varMatchedDirector)\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "UNWIND split(data.Producer, ',') AS producer\n" +
            "MERGE (varProducer:Producer {name: producer})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "MATCH (movie:Movie {title: data.Title})\n" +
            "UNWIND split(data.Producer, ',') AS matchedProducer\n" +
            "MATCH (varMatchedProducer:Producer {name: matchedProducer})\n" +
            "MERGE (movie)-[r:PRODUCED_BY]->(varMatchedProducer)\n" +
            "MERGE (movie)<-[rb:PRODUCED]-(varMatchedProducer)\n"+
            "WITH data WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "UNWIND split(data.Starring, ',') AS starring \n" +
            "MERGE (varStarring:Starring {name: starring})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "MATCH (movie:Movie {title: data.Title})\n" +
            "UNWIND split(data.Starring, ',') AS matchedStarring\n" +
            "MATCH (varMatchedStarring:Starring {name: matchedStarring})\n" +
            "MERGE (movie)-[r:HAS_CAST]->(varMatchedStarring)\n" +
            "MERGE (movie)<-[rb:CASTED_IN]-(varMatchedStarring)\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "MERGE (year:ReleasedYear {year: data.Year})\n" +
            "WITH data WHERE NOT (data.Title='null' AND data.Starring='null' AND data.Director='null' AND data.Year='null') \n" +
            "MATCH (movie:Movie {title: data.Title})\n" +
            "MATCH (year:ReleasedYear {year: data.Year})\n" +
            "MERGE (movie)-[r:RELEASED_IN]->(year)\n" +
            "MERGE (movie)<-[rb:HAS_MOVIE]-(year)\n")
            public Movie saveMovie(@Param("movie") Movie movie);
}


