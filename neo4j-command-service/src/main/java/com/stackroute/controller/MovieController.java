package com.stackroute.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.Movie;
import com.stackroute.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collection;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // method to save json data into neo4j as nodes and relationships
    @PostMapping("/saveMovie")
    public Collection<Movie> saveMovie(@RequestBody Movie movieString) throws IOException {
//        Movie movie = new ObjectMapper().readValue(movieString, Movie.class);
        System.out.println(movieString);
        System.out.println("The json "+movieString);
//        return null;
        return (Collection<Movie>) movieService.saveMovie(movieString);
    }
}
