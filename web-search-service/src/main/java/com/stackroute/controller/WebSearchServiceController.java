package com.stackroute.controller;

import com.stackroute.modals.Result;
import com.stackroute.service.WebSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.util.List;

//controller to get links from the api
@RestController
public class WebSearchServiceController {

    @Autowired
    private WebSearchService searchService;

    public WebSearchServiceController(WebSearchService searchService) {
        this.searchService = searchService;
    }

    //getMapping to get the links from the api
    @GetMapping("/search")
    public ResponseEntity<List<Result>> getSearchResults(@RequestParam String searchString) throws URISyntaxException {
        return new ResponseEntity<List<Result>>(searchService.getSearchResults(searchString), HttpStatus.OK);
    }

    //saving the links in the database
    @PostMapping("/result")
    public ResponseEntity<?> saveResults(@RequestBody List<Result> resultList) throws URISyntaxException {
        System.out.println(resultList);
        List<Result> savedResult = searchService.saveResults(resultList);
        return new ResponseEntity<>(savedResult,HttpStatus.OK);
    }
}
