package me.cristiangomez.news.feed;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import me.cristiangomez.news.R;
import me.cristiangomez.news.data.DrawerItem;

public class FeedActivity extends AppCompatActivity {
    private FeedPresenter feedPresenter;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView drawerListView;
    private DrawerListAdapter drawerListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_act);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FeedFragment feedFragment = (FeedFragment) getSupportFragmentManager()
                .findFragmentById(R.id.feed_content);
        mDrawerLayout = findViewById(R.id.feed_drawer);
        drawerListView = findViewById(R.id.feed_drawer_list);
        List<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem("about", R.string.nav_about, R.drawable.ic_info_outline_black_24dp));
        DrawerListAdapter drawerListAdapter = new DrawerListAdapter(this, drawerItems);
        drawerListView.setAdapter(drawerListAdapter);

        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        feedPresenter = new FeedPresenter(feedFragment);
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.syncState();
    }
}
