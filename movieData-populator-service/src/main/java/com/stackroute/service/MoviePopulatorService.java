package com.stackroute.service;


import java.io.IOException;

public interface MoviePopulatorService {

    void fetchDataFromOmdbApi() throws IOException;
    void saveDataFromOmdbApiToNeo4j()throws IOException;
}
