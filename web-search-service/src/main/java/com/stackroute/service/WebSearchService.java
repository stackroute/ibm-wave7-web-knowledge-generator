package com.stackroute.service;

import com.stackroute.modals.Result;
import java.net.URISyntaxException;
import java.util.List;

//Interface
public interface WebSearchService {
    //method to search and get the links from the api
    List<Result> getSearchResults(String searchString) throws URISyntaxException;
    //method to save the links
    List<Result> saveResults(List<Result> resultList) throws URISyntaxException;
}

