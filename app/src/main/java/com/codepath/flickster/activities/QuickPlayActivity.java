package com.codepath.flickster.activities;

import android.os.Bundle;

import com.codepath.flickster.BuildConfig;
import com.codepath.flickster.R;
import com.codepath.flickster.controllers.ApiManager;
import com.codepath.flickster.controllers.Dialogs;
import com.codepath.flickster.interfaces.VideosListener;
import com.codepath.flickster.models.Video;
import com.codepath.flickster.models.VideosResults;
import com.codepath.flickster.utils.Constants;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuickPlayActivity extends YouTubeBaseActivity implements VideosListener {

    @BindView(R.id.player) YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_play);
        ButterKnife.bind(this);
        ApiManager.instance().requestVideosList(this, getIntent().getStringExtra(Constants.MOVIE_ID));
    }

    @Override
    public void onVideosLoaded(final VideosResults results) {
        runOnUiThread(() -> youTubePlayerView.initialize(BuildConfig.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                for (Video video : results.videos) {
                    if (video.name.contains("Trailer")) {
                        youTubePlayer.loadVideo(video.key);
                        return;
                    }
                }
                youTubePlayer.loadVideo(results.videos.get(0).key);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        }));
    }

    @Override
    public void onError() {
        runOnUiThread(() -> Dialogs.getInstance().showAlert(this, () -> ApiManager.instance().requestVideosList(this, getIntent().getStringExtra(Constants.MOVIE_ID))));
    }
}