package me.cristiangomez.news.feed;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.Story;

public class FeedPresenter implements FeedContract.Presenter {
    @Override
    public void loadStories() {
        // TODO: implement logic
    }

    @Override
    public void openStoryDetails(@NonNull Story requestedStory) {
        // TODO: implement logic
    }

    @Override
    public void start() {
        loadStories();
    }
}
