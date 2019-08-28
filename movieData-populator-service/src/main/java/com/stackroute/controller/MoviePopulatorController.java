package com.stackroute.controller;

import com.stackroute.service.MoviePopulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MoviePopulatorController {

    @Autowired
    MoviePopulatorService moviePopulatorService;

    public MoviePopulatorController(MoviePopulatorService moviePopulatorService) {
        this.moviePopulatorService = moviePopulatorService;
    }

    @GetMapping("fetch")
    public void fetchDataFromOmdbApi(){
        System.out.println("controller");
        moviePopulatorService.fetchDataFromOmdbApi();
    }
}
