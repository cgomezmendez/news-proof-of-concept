package me.cristiangomez.news.feed;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import me.cristiangomez.news.R;
import me.cristiangomez.news.data.DrawerItem;
import me.cristiangomez.news.data.Image;
import me.cristiangomez.news.util.ImageDownloadTask;

public class DrawerListAdapter extends ArrayAdapter<DrawerItem> {

    public DrawerListAdapter(Context context, List<DrawerItem> stories) {
        super(context, R.layout.feed_drawer_item, stories);
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

        DrawerItem story = getItem(position);
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

        void bind(DrawerItem story) {
            this.title.setText(story.getWebTitle());
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
                }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, story.getThumbnail());
            } else {
                this.progressBar.setVisibility(View.INVISIBLE);
                this.imageUri = null;
                this.thumbnail.setImageBitmap(null);
                this.thumbnail.setImageResource(R.drawable.ic_image_black_24dp);
            }
        }
    }
}
