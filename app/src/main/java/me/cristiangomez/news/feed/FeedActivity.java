package me.cristiangomez.news.feed;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import me.cristiangomez.news.R;

public class FeedActivity extends AppCompatActivity {
    private FeedPresenter feedPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_act);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FeedFragment feedFragment = (FeedFragment) getSupportFragmentManager()
                .findFragmentById(R.id.feed_content);
        feedPresenter = new FeedPresenter(feedFragment);
    }
}
