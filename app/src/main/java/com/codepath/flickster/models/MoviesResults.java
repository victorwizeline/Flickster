package com.codepath.flickster.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResults {

    @SerializedName("results")
    public List<Movie> movies;
}
