package me.cristiangomez.news.data.source.local;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.source.StoriesDataSource;

public class StoryLocalDataSource implements StoriesDataSource {
    @Override
    public void getStories(@NonNull LoadStoriesCallback callback, int page, String section) {
        //TODO: add logic (I'm tired I don't know if I'm going to implement this)
        callback.onNoDataAvailable();
    }

    @Override
    public void getStory(@NonNull LoadStoryCallback callback, String id) {
        //TODO: Implement logic
    }
}
