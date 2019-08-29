package com.stackroute.controller;


import com.stackroute.service.WebPageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//controller to fetch websiteData
@RestController
public class WebPageController {

    private ResponseEntity responseEntity;
    private WebPageService webPageService;

    public WebPageController(WebPageService webPageService) throws IOException {
        this.webPageService = webPageService;
    }

    //getMapping to fetch the reqiured contents from the url
    @GetMapping("getContent")
    //Controller method to return the data
    public ResponseEntity<String> getAllContent(@RequestParam(value = "url") String url) throws IOException {
       String result = "";
        System.out.println(url);
       result = webPageService.getTitle(url)+"\n\n\n";
        result = result + webPageService.getHeading(url)+"\n\n\n";
        result = result + webPageService.getAllPTextsFromBody(url)+"\n\n\n";
//        result = result + webPageService.printImages()+"\n\n\n";
        responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
        return responseEntity;
    }
}
