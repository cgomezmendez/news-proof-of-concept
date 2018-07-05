package me.cristiangomez.news.story;

public class StoryPresenter implements StoryContract.Presenter {
    private String storyId;

    @Override
    public void loadStory() {

    }

    @Override
    public void start() {
        loadStory();
    }

    @Override
    public void setStoryId(String storyId) {

    }
}
