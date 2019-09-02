package com.stackroute.controller;

import com.stackroute.service.MoviePopulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MoviePopulatorController {

    @Autowired
    MoviePopulatorService moviePopulatorService;

    public MoviePopulatorController(MoviePopulatorService moviePopulatorService) {
        this.moviePopulatorService = moviePopulatorService;
    }

    @GetMapping("fetch")
    public void fetchDataFromOmdbApi() throws IOException {
        moviePopulatorService.fetchDataFromOmdbApi();
    }
    // method to store movie data from Omdb to neo4j
    @GetMapping("export-movies")
    public void fetch() throws IOException {
        moviePopulatorService.saveDataFromOmdbApiToNeo4j();
    }
}
