package me.cristiangomez.news.story;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.story_frag, container, false);
    }

    @Override
    public void showStory(final Story story) {
        requireActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
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
}
