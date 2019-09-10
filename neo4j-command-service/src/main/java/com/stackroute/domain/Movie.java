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
    @JsonProperty("Starring")
    private String starring;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("ProductionHouse")
    private String productionHouse;
    @JsonProperty("Producer")
    private String producer;

    public Movie() {
    }

    public Movie(String title, String year, String starring, String director, String productionHouse, String producer) {
        this.title = title;
        this.year = year;
        this.starring = starring;
        this.director = director;
        this.productionHouse = productionHouse;
        this.producer = producer;
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

    public String getStarring() {
        return starring;
    }

    public void setStarring(String starring) {
        this.starring = starring;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductionHouse() {
        return productionHouse;
    }

    public void setProductionHouse(String production) {
        this.productionHouse = production;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", starring='" + starring + '\'' +
                ", director='" + director + '\'' +
                ", productionHouse='" + productionHouse + '\'' +
                ", producer='" + producer + '\'' +
                '}';
    }
}
