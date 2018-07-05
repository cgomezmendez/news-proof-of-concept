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
import me.cristiangomez.news.data.Image;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.data.StorySection;
import me.cristiangomez.news.util.ImageDownloadTask;

public class FeedSectionsAdapter extends ArrayAdapter<StorySection> {

    public FeedSectionsAdapter(Context context, List<StorySection> sections) {
        super(context, R.layout.feed_story, sections);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_section_item,
                    parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        StorySection section = getItem(position);
        holder.bind(section);
        return convertView;
    }

    static class ViewHolder {
        private TextView title;

        ViewHolder(View view) {
            this.title = view.findViewById(R.id.section_title);
        }

        void bind(StorySection section) {
            this.title.setText(section.getName());
        }
    }
}
