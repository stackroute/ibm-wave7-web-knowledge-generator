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
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@Service
public class MoviePopulatorServiceImpl implements MoviePopulatorService {

    @Autowired
    MoviePopulatorRepository moviePopulatorRepository;

    public MoviePopulatorServiceImpl(MoviePopulatorRepository moviePopulatorRepository) {
        this.moviePopulatorRepository = moviePopulatorRepository;
    }

    @Override
    public void fetchDataFromOmdbApi() {
//
            System.out.println("service");
            final String uri = "http://www.omdbapi.com/?apikey=43866b21&i=tt0048545";
            RestTemplate restTemplate = new RestTemplate();
           // ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
            ResponseEntity<String> result = restTemplate.getForEntity(uri,String.class);

            System.out.println(result.getBody());
//            System.out.println(result.getBody());
//
//
//            //Object Mapper to access the JSON from the response entity
////            ObjectMapper mapper = new ObjectMapper();
////            JsonNode arrayNode = null;
//
//
//
//            //read the response body to get JSON object
//
////            try {
////                arrayNode = mapper.readTree(result.getBody());
////            } catch (IOException e) {
////                e.printStackTrace();
////            }
//
//            JSONObject output;
//            try {
////                output = new JSONObject(result.getBody());
////                System.out.println(output);
////                JSONArray docs = new JSONArray(output.toString());
////                System.out.println(docs);
//
//                File file=new File("/moviedata.csv");
//                String csv = CDL.toString(result.getBody());
//                FileUtils.writeStringToFile(file, csv);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            //  ArrayNode arrayNode = (ArrayNode) root;
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
////                System.out.println("before repo");
////                    moviePopulatorRepository.saveMovie(result.getBody());
////                System.out.println("after repo");


        String jsonString = result.getBody();

        JSONObject output;

            try {
                output = new JSONObject(jsonString);
                System.out.println(output);
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(output);
                File file=new File("./moviedata.csv");
                String csv = CDL.toString(jsonArray);
                FileUtils.writeStringToFile(file, csv);
                System.out.println("Data has been Sucessfully Writeen to "+file);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
//                // TODO Auto-generated catch block
                e.printStackTrace();
            }


    }
}