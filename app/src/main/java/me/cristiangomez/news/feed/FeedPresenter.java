package me.cristiangomez.news.feed;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.Story;

public class FeedPresenter implements FeedContract.Presenter {
    private final FeedContract.View feedView;

    public FeedPresenter(@NonNull  FeedContract.View feedView) {
        this.feedView = feedView;
        feedView.setPresenter(this);
        Json
    }

    @Override
    public void loadStories() {
        // TODO: implement logic
        List<Story> stories = new ArrayList<>();
        stories.add(new Story("sport/live/2018/jul/04/wimbledon-2018-federer-serena-williams-venus-swan-wozniacki-cilic-day-three-live",
                Uri.parse("https://www.theguardian.com/sport/live/2018/jul/04/wimbledon-2018-federer-serena-williams-venus-swan-wozniacki-cilic-day-three-live"),
                Uri.parse("https://content.guardianapis.com/sport/live/2018/jul/04/wimbledon-2018-federer-serena-williams-venus-swan-wozniacki-cilic-day-three-live")))
        this.feedView.showStories(stories);
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
