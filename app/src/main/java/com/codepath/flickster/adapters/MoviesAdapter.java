package com.codepath.flickster.adapters;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.activities.MovieDetailActivity;
import com.codepath.flickster.activities.QuickPlayActivity;
import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codepath.flickster.utils.Constants.*;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> movies;

    public MoviesAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(getLayout(viewType), parent, false);
        return new ViewHolder(view);
    }

    private int getLayout(int viewType) {
        int layout;
        switch (viewType) {
            case LAYOUT_TYPE_1:
                layout = R.layout.item_movie;
                break;
            case LAYOUT_TYPE_2:
                layout = R.layout.item_movie_more;
                break;
            default:
                layout = R.layout.item_movie;
                break;
        }
        return layout;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).voteAverage < 5) {
            return LAYOUT_TYPE_1;
        } else {
            return LAYOUT_TYPE_2;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivMovieImage) ImageView ivMovieImage;
        @Nullable @BindView(R.id.tvTitle) TextView tvTitle;
        @Nullable @BindView(R.id.tvOverview) TextView tvOverview;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(final Movie movie) {
            String posterPath = String.format(IMAGE_PREFIX, movie.posterPath);
            String backdropPath = String.format(IMAGE_PREFIX, movie.backdropPath);
            String imagePath = movie.voteAverage < 5 ? posterPath : backdropPath;
            Picasso.with(itemView.getContext())
                    .load(imagePath)
                    .placeholder(R.drawable.flickster)
                    .into(ivMovieImage);

            if (movie.voteAverage < 5 && tvTitle != null && tvOverview != null) {
                tvTitle.setText(movie.originalTitle);
                tvOverview.setText(movie.overview);
                itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(itemView.getContext(), MovieDetailActivity.class);
                    intent.putExtra(MOVIE, movie);
                    itemView.getContext().startActivity(intent);
                });
            } else {
                itemView.setOnClickListener(v -> {
                    Intent intent = new Intent(itemView.getContext(), QuickPlayActivity.class);
                    intent.putExtra(MOVIE_ID, String.valueOf(movie.id));
                    itemView.getContext().startActivity(intent);
                });
            }
        }
    }
}
