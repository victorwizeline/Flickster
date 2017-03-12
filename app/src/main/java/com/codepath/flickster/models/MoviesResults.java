package com.codepath.flickster.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by victor on 3/11/17.
 */

public class MoviesResults {

    @SerializedName("results")
    public List<Movie> movies;
}
