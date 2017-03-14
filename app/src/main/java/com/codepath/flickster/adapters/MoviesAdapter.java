package com.codepath.flickster.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.codepath.flickster.viewholders.LessPopularViewHolder;
import com.codepath.flickster.viewholders.MostPopularViewHolder;

import java.util.List;

import static com.codepath.flickster.utils.Constants.*;

public class MoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Movie> movies;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).voteAverage < POPULAR_MOVIE_AVERAGE) {
            return LAYOUT_TYPE_1;
        } else {
            return LAYOUT_TYPE_2;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case LAYOUT_TYPE_1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
                return new LessPopularViewHolder(view);
            case LAYOUT_TYPE_2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_more, parent, false);
                return new MostPopularViewHolder(view);
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
                return new LessPopularViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case LAYOUT_TYPE_1:
                ((LessPopularViewHolder) holder).bind(movies.get(position));
                break;
            case LAYOUT_TYPE_2:
                ((MostPopularViewHolder) holder).bind(movies.get(position));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
