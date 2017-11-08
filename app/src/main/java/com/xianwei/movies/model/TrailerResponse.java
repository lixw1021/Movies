package com.xianwei.movies.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xianwei li on 11/8/2017.
 */

public class TrailerResponse {
    @SerializedName("id")
    private Integer id;
    @SerializedName("results")
    private List<Trailer> results = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public List<Trailer> getResults() {
        return results;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResults(List<Trailer> results) {
        this.results = results;
    }
}
