package me.cristiangomez.news.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import me.cristiangomez.news.R;
import me.cristiangomez.news.data.Story;

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
            holder = new ViewHolder();
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Story story = getItem(position);
        return convertView;
    }

    static class ViewHolder {
        //TODO: add logic
    }
}
