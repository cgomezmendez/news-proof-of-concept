package me.cristiangomez.news.story;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ShareActionProvider;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import me.cristiangomez.news.R;
import me.cristiangomez.news.data.Image;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.feed.FeedListAdapter;
import me.cristiangomez.news.util.ImageDownloadTask;

public class StoryFragment extends Fragment implements StoryContract.View {
    StoryContract.Presenter presenter;
    private ImageView thumbnailView;
    private ProgressBar progressBarView;
    private TextView titleView;
    private WebView contentView;
    private ShareActionProvider shareActionProvider;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true  );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.story_frag, container, false);
        thumbnailView = view.findViewById(R.id.story_thumbnail);
        progressBarView = view.findViewById(R.id.story_thumbnail_progress_bar);
        titleView = view.findViewById(R.id.story_title);
        contentView = view.findViewById(R.id.story_content);
        return view;
    }

    @Override
    public void showStory(final Story story) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                titleView.setText(story.getWebTitle());
                contentView.getSettings().setJavaScriptEnabled(true);
                contentView.loadData(story.getBody(), "text/html", "UTF-8");
                if (story.getThumbnail() != null && !story.getThumbnail().toString().isEmpty()) {
                    new ImageDownloadTask(new ImageDownloadTask.ImageDownloadCallback() {
                        @Override
                        public void onImageDownloaded(Image image) {
                            if (image != null && image.getBitmap() != null) {
                                progressBarView.setVisibility(View.INVISIBLE);
                                thumbnailView.setImageBitmap(image.getBitmap());
                            }
                        }
                    }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, story.getThumbnail());
                } else {
                    thumbnailView.setImageResource(R.drawable.ic_image_black_24dp);
                    progressBarView.setVisibility(View.GONE);
                }
                Intent shareIntent = new Intent();
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, story.getWebTitle());
                shareIntent.putExtra(Intent.EXTRA_TEXT, story.getWebUrl());
                setShareIntent(shareIntent);
            }
        });
    }

    @Override
    public void setPresenter(StoryContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.story, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_item_share);
        shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setShareIntent(Intent intent) {
        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(intent);
        }
    }
}
