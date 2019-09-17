package com.stackroute.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.model.Node;
import com.stackroute.model.Node1;
import com.stackroute.model.Result;
import com.stackroute.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/neo4j")
public class MovieController {
    private MovieService movieService;
   private  Collection<Node> responseEntity;
    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }
    @Autowired
    private KafkaTemplate<String, Result> kafkaTemplate;
    private static final String TOPIC = "SearchResult";


    String input="";
    Node1 node=new Node1();
    ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "Search-nlp", groupId = "group_id")
    public void consumer(String mapper) throws IOException, ClassNotFoundException {
    this.input=mapper;
        Node1 node1 = objectMapper.readValue(mapper, Node1.class);
        this.node=node1;
        System.out.println("consumed url is:"+mapper);
        System.out.println(node1);
        getdata();
    }


    @PostMapping("/graph")
    public ResponseEntity<Result> getdata() throws ClassNotFoundException {

        System.out.println(("inside mapping"));
        ResponseEntity<Result> response= new ResponseEntity<Result>(movieService.getData(node),HttpStatus.OK);
        Result result=movieService.getData(node);
        System.out.println("data to be published    "+result);
        this.kafkaTemplate.send(TOPIC,result);
        System.out.println("published");
        return new ResponseEntity<Result>(movieService.getData(node), HttpStatus.OK);
    }

}