package com.stackroute.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.stackroute.domain.Movie;
import com.stackroute.repository.MoviePopulatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.sound.midi.Track;
import java.io.IOException;

@Service
public class MoviePopulatorServiceImpl implements MoviePopulatorService {

    @Autowired
    MoviePopulatorRepository moviePopulatorRepository;

    public MoviePopulatorServiceImpl(MoviePopulatorRepository moviePopulatorRepository) {
        this.moviePopulatorRepository = moviePopulatorRepository;
    }

    @Override
    public void fetchDataFromOmdbApi() {
        {
            System.out.println("service");
            final String uri = "http://www.omdbapi.com/?apikey=43866b21&i=tt0048545";
            RestTemplate restTemplate = new RestTemplate();
           // ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            ResponseEntity<JsonNode> result = restTemplate.getForEntity(uri,JsonNode.class);

            System.out.println(result);
            System.out.println(result.getBody());


            //Object Mapper to access the JSON from the response entity
            ObjectMapper mapper = new ObjectMapper();
            JsonNode arrayNode = null;



            //read the response body to get JSON object

//                arrayNode = mapper.readTree(result.getBody());
              //  ArrayNode arrayNode = (ArrayNode) root;
                    //get a new Track object and fill it with data using setters
//                     Movie movie =  new Movie();
//                movie.setTitle(arrayNode.path("Title").asText());
//                movie.setReleaseYear(arrayNode.path("Year").asText());
//                movie.setReleaseDate(arrayNode.path("Released").asText());
//                movie.setDirector(arrayNode.path("Director").asText());
//                movie.setGenre(arrayNode.path("Genre").asText());
//                movie.setAwards(arrayNode.path("Awards").asText());
//                movie.setLanguage(arrayNode.path("Language").asText());
//                movie.setCountry(arrayNode.path("Country").asText());
//                movie.setProduction(arrayNode.path("Production").asText());
//                movie.setSummary(arrayNode.path("Plot").asText());
//                movie.setIbdbId(arrayNode.path("imdbID").asText());
//                movie.setRated(arrayNode.path("Rated").asText());
//                movie.setPoster(arrayNode.path("Poster").asText());
//
//                System.out.println(movie);

                //save the track to database
                System.out.println("before repo");
                    moviePopulatorRepository.saveMovie(result.getBody());
                System.out.println("after repo");



        }
    }
}