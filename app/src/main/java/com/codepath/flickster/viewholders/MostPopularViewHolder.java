package com.codepath.flickster.viewholders;

import android.content.Intent;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.codepath.flickster.R;
import com.codepath.flickster.activities.QuickPlayActivity;
import com.codepath.flickster.models.Movie;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codepath.flickster.utils.Constants.IMAGE_PREFIX;
import static com.codepath.flickster.utils.Constants.MOVIE_ID;

public class MostPopularViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivMovieImage) ImageView ivMovieImage;

    public MostPopularViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Movie movie) {
        String posterPath = String.format(IMAGE_PREFIX, movie.backdropPath);
        Picasso.with(itemView.getContext()).load(posterPath).placeholder(R.drawable.flickster).into(ivMovieImage);
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(itemView.getContext(), QuickPlayActivity.class);
            intent.putExtra(MOVIE_ID, String.valueOf(movie.id));
            itemView.getContext().startActivity(intent);
        });
    }
}