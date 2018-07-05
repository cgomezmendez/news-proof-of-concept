package me.cristiangomez.news.feed;

import android.os.AsyncTask;

import java.util.List;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.StoriesRepository;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.data.source.StoriesDataSource;
import me.cristiangomez.news.data.source.local.StoryLocalDataSource;
import me.cristiangomez.news.data.source.remote.StoriesRemoteDataSource;

public class FeedPresenter implements FeedContract.Presenter {
    private final FeedContract.View feedView;
    private StoriesRepository storiesRepository;

    public FeedPresenter(@NonNull  FeedContract.View feedView) {
        this.feedView = feedView;
        feedView.setPresenter(this);
        storiesRepository = StoriesRepository.getInstance(StoriesRemoteDataSource.getInstance(),
                new StoryLocalDataSource());
    }

    @Override
    public void loadStories(final int page) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                storiesRepository.getStories(new StoriesDataSource.LoadStoriesCallback() {
                    @Override
                    public void onStoriesLoaded(List<Story> stories) {
                        FeedPresenter.this.feedView.showStories(stories);
                    }
                    @Override
                    public void onNoDataAvailable() {

                    }
                }, page);
            }
        });
    }

    @Override
    public void openStoryDetails(@NonNull Story requestedStory) {
        // TODO: implement logic
        feedView.showStoryDetailsUi(requestedStory.getId());
    }

    @Override
    public void start() {
        loadStories(1);
    }
}
