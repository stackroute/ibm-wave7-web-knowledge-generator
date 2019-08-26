package com.stackroute.service;

import com.stackroute.modals.Result;

import java.net.URISyntaxException;
import java.util.List;

//Interface
public interface WebSearchService {

    List<Result> getSearchResults(String searchString) throws URISyntaxException;
    List<Result> saveResults(List<Result> resultList) throws URISyntaxException;

}

