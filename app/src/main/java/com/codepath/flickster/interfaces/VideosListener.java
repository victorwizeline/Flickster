package com.codepath.flickster.interfaces;

import com.codepath.flickster.models.VideosResults;

/**
 * Created by victor on 3/10/17.
 */

public interface VideosListener {
    void onVideosLoaded(VideosResults results);
    void onError();
}
