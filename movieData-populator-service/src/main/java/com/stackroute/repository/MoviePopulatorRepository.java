package com.stackroute.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.stackroute.domain.Movie;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoviePopulatorRepository extends Neo4jRepository<Movie, Long> {

    @Query("WITH {json} as data MERGE (movie:Movie {id:data.imdbID}) ON CREATE SET movie.title = data.Title, movie.releaseDate = data.Released, movie.summary = data.Plot, movie.poster = data.Poster, movie.duration = data.Runtime MERGE (year:Year {id:data.Year}) MERGE (movie)-[:RELEASED_IN]->(year) FOREACH (rate IN data.Ratings | MERGE (movie)<-[:HAS_RATING]-(rating:Ratings {source: rate.Source, value:rate.Value})) MERGE (director:Director) ON CREATE SET director.name = data.Director MERGE (movie)-[:DIRECTED_BY]->(director)")
    void saveMovie(JsonNode movieNode);
}
//@Query("MERGE (movie:Movie {id:1}) ON CREATE SET question.title = \"my question\", question.share_link = \"q.share_link\", question.favorite_count = 22 MERGE (owner:User {id:1}) ON CREATE SET owner.display_name = \"Shubhi\" MERGE (owner)-[:ASKED]->(question) MERGE (question)<-[:ANSWERS]-(answer:Answer {id:1}) MERGE (answerer:User {id:2}) ON CREATE SET answerer.display_name = \"suchita\" MERGE (answer)<-[:PROVIDED]-(answerer)")
