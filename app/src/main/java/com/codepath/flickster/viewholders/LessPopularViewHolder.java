package com.codepath.flickster.viewholders;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.activities.MovieDetailActivity;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codepath.flickster.utils.Constants.IMAGE_PREFIX;
import static com.codepath.flickster.utils.Constants.MOVIE;

public class LessPopularViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivMovieImage) ImageView ivMovieImage;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvOverview) TextView tvOverview;

    public LessPopularViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Movie movie) {
        String posterPath = String.format(IMAGE_PREFIX, movie.posterPath);
        Picasso.with(itemView.getContext()).load(posterPath).placeholder(R.drawable.flickster).into(ivMovieImage);
        tvTitle.setText(movie.originalTitle);
        tvOverview.setText(movie.overview);
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(itemView.getContext(), MovieDetailActivity.class);
            intent.putExtra(MOVIE, movie);
            itemView.getContext().startActivity(intent);
        });
    }
}