package com.codepath.flickster.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosResults {

    @SerializedName("results")
    public List<Video> videos;
}
