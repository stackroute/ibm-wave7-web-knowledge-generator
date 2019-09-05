package com.stackroute.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = false)
public class Movie {
    // properties of movie
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private String year;
    @JsonProperty("Actor")
    private String actor;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Production")
    private String production;
    @JsonProperty("Writer")
    private String writer;

    public Movie() {
    }

    public Movie(String title, String year, String actor, String director, String production, String writer) {
        this.title = title;
        this.year = year;
        this.actor = actor;
        this.director = director;
        this.production = production;
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProduction() {
        return production;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", actor='" + actor + '\'' +
                ", director='" + director + '\'' +
                ", production='" + production + '\'' +
                ", writer='" + writer + '\'' +
                '}';
    }
}
