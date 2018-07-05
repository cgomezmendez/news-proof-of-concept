package me.cristiangomez.news.feed;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.Html;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import me.cristiangomez.news.R;
import me.cristiangomez.news.data.Image;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.util.ImageDownloadTask;
import me.cristiangomez.news.util.ImageDownloader;

public class FeedListAdapter extends ArrayAdapter<Story> {

    public FeedListAdapter(Context context, List<Story> stories) {
        super(context, R.layout.feed_story, stories);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_story,
                    parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Story story = getItem(position);
        holder.bind(story);
        return convertView;
    }

    static class ViewHolder {
        private ImageView thumbnail;
        private TextView title;
        private TextView summary;
        private ProgressBar progressBar;
        private Uri imageUri;

        ViewHolder(View view) {
            this.thumbnail = view.findViewById(R.id.story_thumbnail);
            this.title = view.findViewById(R.id.story_title);
            this.summary = view.findViewById(R.id.story_summary);
            this.progressBar = view.findViewById(R.id.story_thumbnail_progress_bar);
        }

        void bind(Story story) {
            this.title.setText(story.getHeadLine());
            this.summary.setText(Html.fromHtml(story.getTrailText()));
            if (story.getThumbnail() != null) {
                this.imageUri = story.getThumbnail();
                this.progressBar.setVisibility(View.VISIBLE);
                this.thumbnail.setImageBitmap(null);
                new ImageDownloadTask(new ImageDownloadTask.ImageDownloadCallback() {
                    @Override
                    public void onImageDownloaded(Image image) {
                        if (image != null && image.getBitmap() != null
                                && image.getUri() == ViewHolder.this.imageUri) {
                            ViewHolder.this.progressBar.setVisibility(View.INVISIBLE);
                            ViewHolder.this.thumbnail.setImageBitmap(image.getBitmap());
                        }
                    }
                }).execute(story.getThumbnail());
            } else {
                this.progressBar.setVisibility(View.INVISIBLE);
                this.imageUri = null;
                this.thumbnail.setImageBitmap(null);
            }
        }
    }
}
