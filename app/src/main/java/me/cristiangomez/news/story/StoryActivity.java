package me.cristiangomez.news.story;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import me.cristiangomez.news.R;

public class StoryActivity extends AppCompatActivity {
    public static final String EXTRA_STORY_ID = "story_id";
    private StoryPresenter storyPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_act);
        StoryFragment storyFragment = (StoryFragment) getSupportFragmentManager()
                .findFragmentById(R.id.story_content);
        String storyID = getIntent().getStringExtra(EXTRA_STORY_ID);
        storyPresenter = new StoryPresenter(storyFragment, storyID);
    }
}
