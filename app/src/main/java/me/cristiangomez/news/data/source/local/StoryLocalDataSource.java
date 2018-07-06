package me.cristiangomez.news.data.source.local;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.source.StoriesDataSource;

public class StoryLocalDataSource implements StoriesDataSource {
    @Override
    public void getStories(@NonNull LoadStoriesCallback callback, int page, String section) {
        //TODO: add logic
        callback.onNoDataAvailable();
    }

    @Override
    public void getStory(@NonNull LoadStoryCallback callback, String id) {
        //TODO: Implement logic
    }
}
