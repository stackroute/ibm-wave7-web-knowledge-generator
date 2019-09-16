package com.stackroute.controller;

import com.stackroute.modals.Result;
import com.stackroute.service.WebSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

//controller to get links from the api
@RestController
@ControllerAdvice(basePackages = "com.stackroute")
public class WebSearchServiceController {

@Autowired
private KafkaTemplate<String,Object> kafkaTemplate;

private Result result;

  private static final String TOPIC = "Kafka_Example";

    @Autowired
    private WebSearchService searchService;

    String input;
    @KafkaListener(topics = "Fetch_Moviename", groupId = "group_id")
    public void consumer(String message) throws IOException,URISyntaxException {

        this.input=message;
        getSearchResults(input);
        //System.out.println(input);
    }



    public WebSearchServiceController(WebSearchService searchService) {
        this.searchService = searchService;
    }

    //getMapping to get the links from the api
    @GetMapping("/search")
    public ResponseEntity<String> getSearchResults(String searchString) throws URISyntaxException {
        searchString=input;
        int i;
        String url;
        ArrayList<String> urlarr = new ArrayList<>();
        System.out.println("consumedd"+searchString);
         String [] stringarr = searchString.replace(" ","_").replace("\"","").split(",");
//        int length=searchString.length();
        //searchString=searchString.substring(1,length-1);
        System.out.println(input.getClass().getName());
        for(i=0;i<stringarr.length;i++){
            System.out.println("WWW"+stringarr[i]);
             url = searchService.getSearchResults(stringarr[i]);
            urlarr.add(url);
            //url=url.substring(7,url.length()-2);
            System.out.println("AAAAAAAAA"+url);
        }


        this.kafkaTemplate.send(TOPIC,urlarr);
        return new ResponseEntity<String>(searchService.getSearchResults(null), HttpStatus.OK);
    }

//    //saving the links in the database
//    @PostMapping("/result")
//    public ResponseEntity<?> saveResults(@RequestBody String resultList) throws URISyntaxException {
//       // Result savedResult=searchService.saveResults(resultList);
//      //System.out.println("------------------------"+result.getUrl());
//       this.kafkaTemplate.send(TOPIC,resultList);
//        return new ResponseEntity<>(resultList,HttpStatus.OK);
//
//    }
}
