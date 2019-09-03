package com.stackroute.controller;


import com.stackroute.model.Node;
import com.stackroute.service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/neo4j")
public class MovieController {
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @GetMapping("/graph/{nodelabel}/{relation}")
    public Collection<Node> getdata(@PathVariable String nodelabel, @PathVariable String relation)
    {
        return (Collection<Node>) movieService.getData(nodelabel,relation);
    }
}
