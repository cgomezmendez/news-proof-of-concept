package me.cristiangomez.news.story;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import me.cristiangomez.news.R;

public class StoryActivity extends AppCompatActivity {
    public static final String EXTRA_STORY_ID = "story_id";
    private StoryPresenter storyPresenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_act);
        StoryFragment storyFragment = (StoryFragment) getSupportFragmentManager()
                .findFragmentById(R.id.story_content);
        String storyID = getIntent().getStringExtra(EXTRA_STORY_ID);
        if (storyFragment != null) {
            storyPresenter = new StoryPresenter(storyFragment, storyID);
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle(null);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
