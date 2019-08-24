package com.stackroute.service;

import com.stackroute.Modals.CodeBeautify;
import com.stackroute.Modals.Result;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public interface WebSearchService {

    List<Result> getSearchResults(String searchString) throws URISyntaxException;
    List<Result> saveResults(List<Result> resultList) throws URISyntaxException;

}

