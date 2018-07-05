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

    public StoryPresenter(StoryContract.View storyView, String storyId) {
        this.storyId = storyId;
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
                storiesRepository.getStory(new StoriesDataSource.LoadStoryCallback() {
                    @Override
                    public void onStoryLoaded(Story story) {
                        storyView.showStory(story);
                    }

                    @Override
                    public void onNoDataAvailable() {

                    }
                }, storyId);
            }
        });
    }

    @Override
    public void start() {
        loadStory();
    }
}
