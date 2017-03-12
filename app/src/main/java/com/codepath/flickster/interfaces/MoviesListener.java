package com.codepath.flickster.interfaces;

import com.codepath.flickster.models.MoviesResults;

/**
 * Created by victor on 3/10/17.
 */

public interface MoviesListener {
    void onMoviesLoaded(MoviesResults moviesResults);
    void onError();
}
