package com.stackroute.modals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Query {
    private String q;
    private String hl;
    private String gl;
    private String location;
    private String search_engine;
    private String apikey;
    private String url;

    //default constructor
    public Query() {
    }

 // Getter Methods

    public String getQ() {
        return q;
    }

    public String getHl() {
        return hl;
    }

    public String getGl() {
        return gl;
    }

    public String getLocation() {
        return location;
    }

    public String getSearch_engine() {
        return search_engine;
    }

    public String getApikey() {
        return apikey;
    }

    public String getUrl() {
        return url;
    }

    // Setter Methods

    public void setQ(String q) {
        this.q = q;
    }

    public void setHl(String hl) {
        this.hl = hl;
    }

    public void setGl(String gl) {
        this.gl = gl;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSearch_engine(String search_engine) {
        this.search_engine = search_engine;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}


