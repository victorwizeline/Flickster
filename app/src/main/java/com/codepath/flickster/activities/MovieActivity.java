package com.codepath.flickster.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codepath.flickster.R;
import com.codepath.flickster.adapters.MoviesAdapter;
import com.codepath.flickster.controllers.Dialogs;
import com.codepath.flickster.interfaces.MoviesListener;
import com.codepath.flickster.controllers.ApiManager;
import com.codepath.flickster.models.MoviesResults;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity implements MoviesListener {

    @BindView(R.id.rvMovies) RecyclerView rvMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        ApiManager.instance().requestMoviesList(this);
    }

    @Override
    public void onMoviesLoaded(final MoviesResults moviesResults) {
        runOnUiThread(() -> {
            rvMovies.setLayoutManager(new LinearLayoutManager(this));
            rvMovies.setAdapter(new MoviesAdapter(moviesResults.movies));
        });
    }

    @Override
    public void onError() {
        runOnUiThread(() -> Dialogs.getInstance().showAlert(this, () -> ApiManager.instance().requestMoviesList(this)));
    }
}
