package com.codepath.flickster.interfaces;

import com.codepath.flickster.models.MoviesResults;

public interface MoviesListener {
    void onMoviesLoaded(MoviesResults moviesResults);
    void onError();
}
