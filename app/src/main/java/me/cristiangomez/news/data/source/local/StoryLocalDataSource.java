package me.cristiangomez.news.data.source.local;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.source.StoriesDataSource;

public class StoryLocalDataSource implements StoriesDataSource {
    @Override
    public void getStories(@NonNull LoadStoriesCallback callback, int page) {
        //TODO: add logic
        callback.onNoDataAvailable();
    }
}
