package com.codepath.flickster.interfaces;

import com.codepath.flickster.models.VideosResults;

public interface VideosListener {
    void onVideosLoaded(VideosResults results);
    void onError();
}
