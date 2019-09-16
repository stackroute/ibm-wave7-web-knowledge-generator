package com.stackroute.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.domain.WebSocketNotificationReceiving;
import com.stackroute.domain.WebSocketNotificationSending;
import com.stackroute.modal.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

@Controller
//STOMP messages routed to @Controller classes
@RestController
public class WebSocketNotificationController {


    @Autowired
    private KafkaTemplate kafkaTemplate;
    private static final String TOPIC = "SearchString";


    String input="";
    Result node=new Result();
    ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = "SearchResult", groupId = "group_id")
    public void consumer(String mapper) throws IOException {
        this.input=mapper;
        Result node1 = objectMapper.readValue(mapper, Result.class);
        this.node=node1;
        System.out.println("consumed url is:"+mapper);
        System.out.println("heloooooooo"+node1);
    }




    public String data [];
        @MessageMapping("/search")
        @SendTo("/topic/result")
        @CrossOrigin
        public WebSocketNotificationSending webSocketNotificationSending(WebSocketNotificationReceiving webSocketNotificationReceiving) throws Exception{
            String searchString = HtmlUtils.htmlEscape(webSocketNotificationReceiving.getName());
            this.kafkaTemplate.send(TOPIC,searchString);
            System.out.println(searchString);
                return  new WebSocketNotificationSending( HtmlUtils.htmlEscape(node.toString()) );
        }

    }//HtmlUtils.htmlEscape(webSocketNotificationReceiving.getName())