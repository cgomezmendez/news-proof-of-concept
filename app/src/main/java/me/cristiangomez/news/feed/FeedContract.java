package me.cristiangomez.news.feed;

import java.util.List;

import androidx.annotation.NonNull;
import me.cristiangomez.news.BasePresenter;
import me.cristiangomez.news.BaseView;
import me.cristiangomez.news.data.Story;

public interface FeedContract {
    interface View extends BaseView<Presenter> {
        void showStories(List<Story> stories);
        void showStoryDetailsUi(String storyId);
    }

    interface Presenter extends BasePresenter {
        void loadStories(int page);
        void openStoryDetails(@NonNull Story requestedStory);
    }
}
