package com.stackroute.controller;

import com.stackroute.modals.Result;
import com.stackroute.service.WebSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.util.List;

//controller to get links from the api
@RestController
@ControllerAdvice(basePackages = "com.stackroute")
public class WebSearchServiceController {

@Autowired
private KafkaTemplate<String,String> kafkaTemplate;

private Result result;

  private static final String TOPIC = "Kafka_Example";

    @Autowired
    private WebSearchService searchService;


  public WebSearchServiceController(WebSearchService searchService) {
        this.searchService = searchService;
    }

    //getMapping to get the links from the api
    @GetMapping("/search")
    public ResponseEntity<Result> getSearchResults(@RequestParam String searchString) throws URISyntaxException {
        return new ResponseEntity<Result>(searchService.getSearchResults(searchString), HttpStatus.OK);
    }

    //saving the links in the database
    @PostMapping("/result")
    public ResponseEntity<?> saveResults(@RequestBody String resultList) throws URISyntaxException {
       // Result savedResult=searchService.saveResults(resultList);
      //System.out.println("------------------------"+result.getUrl());
       this.kafkaTemplate.send(TOPIC,resultList);
        return new ResponseEntity<>(resultList,HttpStatus.OK);

    }
}
