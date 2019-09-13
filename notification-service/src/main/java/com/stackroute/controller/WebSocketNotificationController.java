package com.stackroute.controller;

import com.stackroute.domain.WebSocketNotificationReceiving;
import com.stackroute.domain.WebSocketNotificationSending;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@Controller
//STOMP messages routed to @Controller classes
@RestController
public class WebSocketNotificationController {


        public String data [];
        @MessageMapping("/search")
        @SendTo("/topic/result")
        @CrossOrigin
        public WebSocketNotificationSending webSocketNotificationSending(WebSocketNotificationReceiving webSocketNotificationReceiving) throws Exception{


            return  new WebSocketNotificationSending("Server response for hkjh " + HtmlUtils.htmlEscape(webSocketNotificationReceiving.getName()) + " is: ");
        }

    }//HtmlUtils.htmlEscape(webSocketNotificationReceiving.getName())