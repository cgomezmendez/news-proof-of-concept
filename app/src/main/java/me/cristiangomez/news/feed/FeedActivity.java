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
        drawerItems.add(new DrawerItem("home", R.string.nav_home, R.drawable.ic_home_black_24dp));
        drawerItems.add(new DrawerItem("World", R.string.nav_world, R.drawable.ic_language_black_24dp));
        drawerItems.add(new DrawerItem("Sports", R.string.nav_sports, R.drawable.ic_directions_bike_black_24dp));
        drawerItems.add(new DrawerItem("edition_australia", R.string.nav_edition_australia, R.drawable.ic_location_on_black_24dp));
        drawerItems.add(new DrawerItem("edition_uk", R.string.nav_edition_uk, R.drawable.ic_location_on_black_24dp));
        drawerItems.add(new DrawerItem("edition_us", R.string.nav_edition_us, R.drawable.ic_location_on_black_24dp));
        drawerItems.add(new DrawerItem("edition_international", R.string.nav_edition_international, R.drawable.ic_location_on_black_24dp));
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
