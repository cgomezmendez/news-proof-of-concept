package me.cristiangomez.news.data.source;

import java.util.List;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.Story;

public interface StoriesDataSource {
    interface LoadStoriesCallback {
        void onStoriesLoaded(List<Story> stories);
        void onNoDataAvailable();
    }

    interface LoadStoryCallback {
        void onStoryLoaded(Story story);
        void onNoDataAvailable();
    }

    void getStories(@NonNull LoadStoriesCallback callback, int page, String section);

    void getStory(@NonNull LoadStoryCallback callback, String id);
}
