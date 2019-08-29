package com.stackroute.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.repository.MoviePopulatorRepository;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.apache.http.*;
import org.neo4j.kernel.impl.factory.GraphDatabaseFacade;
import org.neo4j.graphdb.*;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static java.util.Collections.singletonMap;
import static org.neo4j.driver.internal.messaging.PackStreamMessageFormatV1.PATH;

@Service
public class MoviePopulatorServiceImpl implements MoviePopulatorService{

    @Autowired
    MoviePopulatorRepository moviePopulatorRepository;

    public MoviePopulatorServiceImpl(MoviePopulatorRepository moviePopulatorRepository) {
        this.moviePopulatorRepository = moviePopulatorRepository;
    }

    @Override
    public void fetchDataFromOmdbApi() throws IOException {
        {
            final String apiUrl = "http://www.omdbapi.com/?apikey=43866b21&i=tt0048545";

            ObjectMapper mapper = new ObjectMapper();

            //GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();

            System.out.println(1);
//            GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase(
//                    new File("data/cars"));
            System.out.println(2);


            CloseableHttpClient http = HttpClients.createMinimal();
            GraphDatabaseService db = new GraphDatabaseFactory().newEmbeddedDatabase( new File("home/shubhi/Documents/web-knowledge-generator/ibm-wave7-web-knowledge-generator/movieData-populator-service/src/main/java/MovieData.csv"));

            System.out.println(3);
            // execute API request and parse response as JSON
            HttpResponse response = http.execute(new HttpGet( apiUrl ));
            Map json = mapper.readValue(response.getEntity().getContent(), Map.class);

            System.out.println(4);
            // execute Cypher
            String query = "WITH {json} as data MERGE (movie:Movie {id:data.imdbID}) ON CREATE SET movie.title = data.Title, movie.releaseDate = data.Released, movie.summary = data.Plot, movie.poster = data.Poster, movie.duration = data.Runtime MERGE (year:Year {id:data.Year}) MERGE (movie)-[:RELEASED_IN]->(year) FOREACH (rate IN data.Ratings | MERGE (movie)<-[:HAS_RATING]-(rating:Ratings {source: rate.Source, value:rate.Value})) MERGE (director:Director) ON CREATE SET director.name = data.Director MERGE (movie)-[:DIRECTED_BY]->(director)";
            db.execute(query, singletonMap("json",json));

            System.out.println(5);
            // application scoped shutdown, or JVM-shutdown-hook
            db.shutdown();












//
//            System.out.println("service");
//            final String uri = "http://www.omdbapi.com/?apikey=43866b21&i=tt0048545";
//            RestTemplate restTemplate = new RestTemplate();
//           // ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
//            ResponseEntity<JsonNode> result = restTemplate.getForEntity(uri,JsonNode.class);
//
//            System.out.println(result);
//            System.out.println(result.getBody());
//
//
//            //Object Mapper to access the JSON from the response entity
//            ObjectMapper mapper = new ObjectMapper();
//            JsonNode arrayNode = null;
//
//
//
//            //read the response body to get JSON object
//
////                arrayNode = mapper.readTree(result.getBody());
//              //  ArrayNode arrayNode = (ArrayNode) root;
//                    //get a new Track object and fill it with data using setters
////                     Movie movie =  new Movie();
////                movie.setTitle(arrayNode.path("Title").asText());
////                movie.setReleaseYear(arrayNode.path("Year").asText());
////                movie.setReleaseDate(arrayNode.path("Released").asText());
////                movie.setDirector(arrayNode.path("Director").asText());
////                movie.setGenre(arrayNode.path("Genre").asText());
////                movie.setAwards(arrayNode.path("Awards").asText());
////                movie.setLanguage(arrayNode.path("Language").asText());
////                movie.setCountry(arrayNode.path("Country").asText());
////                movie.setProduction(arrayNode.path("Production").asText());
////                movie.setSummary(arrayNode.path("Plot").asText());
////                movie.setIbdbId(arrayNode.path("imdbID").asText());
////                movie.setRated(arrayNode.path("Rated").asText());
////                movie.setPoster(arrayNode.path("Poster").asText());
////
////                System.out.println(movie);
//
//                //save the track to database
//                System.out.println("before repo");
//            System.out.println(result.getBody());
//                    moviePopulatorRepository.saveMovie(result.getBody());
//                System.out.println("after repo");



        }
    }
}