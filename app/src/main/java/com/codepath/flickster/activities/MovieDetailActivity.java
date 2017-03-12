package com.codepath.flickster.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.flickster.R;
import com.codepath.flickster.models.Movie;
import com.codepath.flickster.utils.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.codepath.flickster.utils.Constants.IMAGE_PREFIX;
import static com.codepath.flickster.utils.Constants.MOVIE_ID;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.ivMovieImage) ImageView ivMovieImage;
    @BindView(R.id.tvTitle) TextView tvTitle;
    @BindView(R.id.tvReleaseDate) TextView tvReleaseDate;
    @BindView(R.id.ratingBar) RatingBar ratingBar;
    @BindView(R.id.tvOverview) TextView tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        bind();
    }

    private void bind() {
        Movie movie = (Movie) getIntent().getSerializableExtra(Constants.MOVIE);
        String imagePath = String.format(IMAGE_PREFIX, movie.backdropPath);
        Picasso.with(this).load(imagePath).placeholder(R.drawable.flickster).into(ivMovieImage);
        tvTitle.setText(movie.originalTitle);
        tvReleaseDate.setText(getString(R.string.release_date).concat(movie.releaseDate));
        ratingBar.setRating(movie.voteAverage);
        ratingBar.setIsIndicator(true);
        tvOverview.setText(movie.overview);
        ivMovieImage.setOnClickListener(v -> {
            Intent intent = new Intent(this, QuickPlayActivity.class);
            intent.putExtra(MOVIE_ID, String.valueOf(movie.id));
            startActivity(intent);
        });
    }
}
