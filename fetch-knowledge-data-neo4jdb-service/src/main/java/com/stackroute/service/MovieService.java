package com.stackroute.service;

import com.stackroute.Repository.MovieRepository;
import com.stackroute.model.Node;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;
     String[] actor= {"actor","star","hero","performer"," player", "trouper", "artist"};

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }




    public Collection<Node> getData(String nodelabel, String relation) {
        for(int i = 0; i < actor.length; i++) {
            if(nodelabel==actor[i])
            {
                nodelabel="Actor";
                break;
            }
            else
            {
                nodelabel="Actor";
            }
        }
        System.out.println(nodelabel);
        System.out.println(relation);
        System.out.println(movieRepository.findData(nodelabel,relation));
        return (Collection<Node>) movieRepository.findData(nodelabel,relation);
    }


}
