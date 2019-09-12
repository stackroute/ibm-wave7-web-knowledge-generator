package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Movie;
import com.stackroute.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;

@RestController
public class MovieController {

    // autowire the movieService
    @Autowired
    private MovieService movieService;

    ResponseEntity responseEntity;

    String input="";
    @KafkaListener(topics = "Fetch-Keyword", groupId = "group_id")
    public void consumer(String message) throws IOException {

        this.input=message;
        System.out.println(input);
    }

    public MovieController(MovieService movieService)
    {
        this.movieService = movieService;
    }

    // method to save json data into neo4j as nodes and relationships
    @PostMapping("/saveMovie")
    public ResponseEntity<?> saveMovie(@RequestBody Movie movieString) throws IOException
    {
        responseEntity = new ResponseEntity<String>(movieService.saveMovie(movieString), HttpStatus.CREATED);
        return responseEntity;
    }
}
