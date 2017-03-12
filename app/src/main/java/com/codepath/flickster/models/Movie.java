package com.codepath.flickster.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by victor on 3/9/17.
 */

public class Movie implements Serializable {

    public Integer id;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("original_title")
    public String originalTitle;
    public String overview;
    @SerializedName("vote_average")
    public Float voteAverage;
    @SerializedName("release_date")
    public String releaseDate;
}
