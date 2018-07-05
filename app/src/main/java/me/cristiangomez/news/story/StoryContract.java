package me.cristiangomez.news.story;

import me.cristiangomez.news.BasePresenter;
import me.cristiangomez.news.BaseView;
import me.cristiangomez.news.data.Story;

public interface StoryContract {
    interface View extends BaseView<Presenter> {
        void showStory(Story story);
    }

    interface Presenter extends BasePresenter {
        void loadStory(String storyId);
    }
}
