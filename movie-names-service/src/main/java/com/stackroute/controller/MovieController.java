package com.stackroute.controller;

import com.stackroute.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("api/v1")
public class MovieController
{

    MovieService movieService;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    private static final String TOPIC = "Fetch_Moviename";


    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @CrossOrigin
    @PostMapping("save/{name}")
    public ResponseEntity<?> saveMovie(@PathVariable("name") String name) {
        System.out.println(name);
        String[] arr = name.split(",");


        System.out.println("AAAA"+arr.length);
            ResponseEntity responseEntity = null;
        int i;
        for(i=1; i<=arr.length; i++)
            {
                System.out.println(i);
                System.out.println(arr[i-1]);
            try {
                    responseEntity = new ResponseEntity<>(movieService.searchByMovieName(arr[i-1]), HttpStatus.OK);
                } catch(Exception e){
                    responseEntity = new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
                }

            }
        this.kafkaTemplate.send(TOPIC, arr);
        System.out.println("Data is produced");

            return responseEntity;

    }


}