package me.cristiangomez.news.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import me.cristiangomez.news.R;
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

        ViewHolder(View view) {
            this.thumbnail = view.findViewById(R.id.story_thumbnail);
            this.title = view.findViewById(R.id.story_title);
            this.summary = view.findViewById(R.id.story_summary);
        }

        void bind(Story story) {
            this.title.setText(story.getHeadLine());
            this.summary.setText(story.getTrailText());
            if (story.getThumbnail() != null) {
                new ImageDownloadTask().execute(story.getThumbnail());
            }
        }
    }
}
