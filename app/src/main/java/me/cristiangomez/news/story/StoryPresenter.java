package me.cristiangomez.news.story;

import android.os.AsyncTask;

import me.cristiangomez.news.data.StoriesRepository;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.data.source.StoriesDataSource;
import me.cristiangomez.news.data.source.local.StoryLocalDataSource;
import me.cristiangomez.news.data.source.remote.StoriesRemoteDataSource;

public class StoryPresenter implements StoryContract.Presenter {
    private String storyId;
    private StoriesRepository storiesRepository;
    private StoryContract.View storyView;
    private Story story;

    public StoryPresenter(StoryContract.View storyView) {
        this.storyView = storyView;
        storyView.setPresenter(this);
        this.storiesRepository = StoriesRepository.getInstance(StoriesRemoteDataSource.getInstance(),
                new StoryLocalDataSource());
    }

    @Override
    public void loadStory() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    @Override
    public void start() {
        loadStory();
    }

    @Override
    public void setStoryId(String storyId) {
        this.storyId = storyId;
    }
}
