package com.stackroute.service;

import com.stackroute.repository.MoviePopulatorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MoviePopulatorServiceImpl implements MoviePopulatorService{

    @Autowired
    MoviePopulatorRepository moviePopulatorRepository;

    public MoviePopulatorServiceImpl(MoviePopulatorRepository moviePopulatorRepository) {
        this.moviePopulatorRepository = moviePopulatorRepository;
    }


    // method to fetch data from omdb and save into neo4j
    @Override
    public void fetchDataFromOmdbApi() throws IOException {

        // call repository method to save movie data into neo4j
        moviePopulatorRepository.saveMovie();
    }

}