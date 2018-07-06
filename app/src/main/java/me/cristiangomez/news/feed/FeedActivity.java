package me.cristiangomez.news.feed;

import android.content.res.Configuration;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
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
        mDrawerLayout = findViewById(R.id.feed_drawer);
        drawerListView = findViewById(R.id.feed_drawer_list);
        List<DrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new DrawerItem("home", R.string.nav_home, R.drawable.ic_home_black_24dp));
        drawerItems.add(new DrawerItem("politics", R.string.nav_politics, R.drawable.ic_record_voice_over_black_24dp));
        drawerItems.add(new DrawerItem("technology", R.string.nav_technology, R.drawable.ic_devices_black_24dp));
        drawerItems.add(new DrawerItem("sport", R.string.nav_sport, R.drawable.ic_directions_bike_black_24dp));
        drawerItems.add(new DrawerItem("world", R.string.nav_world, R.drawable.ic_language_black_24dp));
        drawerItems.add(new DrawerItem("australia-news", R.string.nav_edition_australia, R.drawable.ic_location_on_black_24dp));
        drawerItems.add(new DrawerItem("uk-news", R.string.nav_edition_uk, R.drawable.ic_location_on_black_24dp));
        drawerItems.add(new DrawerItem("us-news", R.string.nav_edition_us, R.drawable.ic_location_on_black_24dp));
        drawerItems.add(new DrawerItem("about", R.string.nav_about, R.drawable.ic_info_outline_black_24dp));
        final DrawerListAdapter drawerListAdapter = new DrawerListAdapter(this, drawerItems);
        drawerListView.setAdapter(drawerListAdapter);
        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                DrawerItem drawerItem = drawerListAdapter.getItem(position);
                mDrawerLayout.closeDrawer(Gravity.LEFT|Gravity.START);
                switch (drawerItem.getId()) {
                    case "home":
                        break;
                    case "about":
                        showAbout();
                        break;
                    default:
                        showSection(drawerItem.getId(), getString(drawerItem.getTitle()));
                        break;
                }
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        showSection(null, getString(R.string.feed_front_page_subtitle));
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

    private void showAbout() {
        new AlertDialog.Builder(FeedActivity.this)
                .setMessage(Html.fromHtml(getString(R.string.about_message)))
                .setCancelable(true)
                .create()
                .show();
    }

    private void showSection(String section, String title) {
        FeedFragment feedFragment = new FeedFragment();
        feedPresenter = new FeedPresenter(feedFragment, section);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.feed_content, feedFragment)
        .commit();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setSubtitle(title);
        }
    }
}
