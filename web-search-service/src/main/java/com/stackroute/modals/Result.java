package com.stackroute.modals;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private int position;

    private String url;

    public Result(int position, String url) {
        this.position = position;
        this.url = url;
    }

    public Result()
    {}

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Result{" +
                "position=" + position +
                ", url='" + url + '\'' +
                '}';
    }
}
