package com.stackroute.service;


import java.io.IOException;

public interface MoviePopulatorService {

    // method to fetch data from omdb
     void fetchDataFromOmdbApi() throws IOException;
}
