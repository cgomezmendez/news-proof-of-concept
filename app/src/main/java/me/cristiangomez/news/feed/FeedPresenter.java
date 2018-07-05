package me.cristiangomez.news.feed;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.data.source.StoriesDataSource;
import me.cristiangomez.news.data.source.remote.StoriesRemoteDataSource;
import me.cristiangomez.news.util.parse.StoryJsonParser;

public class FeedPresenter implements FeedContract.Presenter {
    private final FeedContract.View feedView;

    public FeedPresenter(@NonNull  FeedContract.View feedView) {
        this.feedView = feedView;
        feedView.setPresenter(this);
    }

    @Override
    public void loadStories(int page) {
        // TODO: implement logic
        StoriesRemoteDataSource remoteDataSource = StoriesRemoteDataSource.getInstance();
        remoteDataSource.getStories(new StoriesDataSource.LoadStoriesCallback() {
            @Override
            public void onStoriesLoaded(List<Story> stories) {
                FeedPresenter.this.feedView.showStories(stories);
            }

            @Override
            public void onNoDataAvailable() {

            }
        });
    }

    @Override
    public void openStoryDetails(@NonNull Story requestedStory) {
        // TODO: implement logic
    }

    @Override
    public void start() {
        loadStories(1);
    }
}
