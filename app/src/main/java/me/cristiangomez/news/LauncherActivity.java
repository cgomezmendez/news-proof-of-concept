package me.cristiangomez.news;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import me.cristiangomez.news.feed.FeedActivity;

/**
 * This activity is in charge of knowing what activity should be shown
 */
public class LauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, FeedActivity.class));
    }
}
