package com.codepath.flickster.controllers;

import android.support.annotation.Nullable;

import com.codepath.flickster.interfaces.MoviesListener;
import com.codepath.flickster.interfaces.VideosListener;
import com.codepath.flickster.models.MoviesResults;
import com.codepath.flickster.models.VideosResults;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.codepath.flickster.utils.Constants.MOVIES;
import static com.codepath.flickster.utils.Constants.VIDEOS;

public class ApiManager {

    private static final ApiManager instance = new ApiManager();

    public static ApiManager instance() {
        return instance;
    }

    private OkHttpClient client;
    private Gson gson;

    private ApiManager() {
        client = new OkHttpClient();
        gson = new Gson();
    }

    public void requestMoviesList(final MoviesListener moviesListener) {
        makeRequest(moviesListener, null, null);
    }

    public void requestVideosList(final VideosListener videosListener, String key) {
        makeRequest(null, videosListener, key);
    }

    private void makeRequest(@Nullable MoviesListener moviesListener, @Nullable VideosListener videosListener, @Nullable String key) {
        String url = moviesListener != null ? MOVIES : String.format(VIDEOS, key);
        client.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (moviesListener != null) {
                    moviesListener.onMoviesLoaded(gson.fromJson(response.body().string(), MoviesResults.class));
                } else if (videosListener != null) {
                    videosListener.onVideosLoaded(gson.fromJson(response.body().string(), VideosResults.class));
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                if (moviesListener != null) {
                    moviesListener.onError();
                } else if (videosListener != null) {
                    videosListener.onError();
                }
            }
        });
    }
}
