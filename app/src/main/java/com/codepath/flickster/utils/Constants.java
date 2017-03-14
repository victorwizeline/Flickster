package com.codepath.flickster.utils;

import com.codepath.flickster.BuildConfig;

public class Constants {

    private final static String BASE_URL = "https://api.themoviedb.org/3/movie/";
    public final static String MOVIES = BASE_URL + "now_playing?api_key=" + BuildConfig.MOVIEDB_API_KEY;
    public final static String VIDEOS = BASE_URL + "%s/videos?api_key=" + BuildConfig.MOVIEDB_API_KEY;
    public final static String IMAGE_PREFIX = "https://image.tmdb.org/t/p/w342/%s";
    public final static String MOVIE_ID = "movie_id";
    public final static String MOVIE = "movie";
    public final static int LAYOUT_TYPE_1 = 1;
    public final static int LAYOUT_TYPE_2 = 2;
    public final static int POPULAR_MOVIE_AVERAGE = 5;
}
