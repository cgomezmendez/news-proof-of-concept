package me.cristiangomez.news.story;

import java.util.List;

import androidx.fragment.app.Fragment;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.feed.FeedContract;

public class StoryFragment extends Fragment implements FeedContract.View {
    @Override
    public void showStories(List<Story> stories) {

    }

    @Override
    public void showStoryDetailsUi(String storyId) {

    }

    @Override
    public void setPresenter(FeedContract.Presenter presenter) {

    }
}
