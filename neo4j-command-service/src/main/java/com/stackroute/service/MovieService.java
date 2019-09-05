package com.stackroute.service;

import com.stackroute.domain.Movie;

import java.util.Collection;


public interface MovieService {

  Collection<Movie> saveMovie(Movie movie);
}
