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

    public DrawerListAdapter(Context context, List<DrawerItem> drawerItems) {
        super(context, R.layout.feed_drawer_item, drawerItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.feed_drawer_item,
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
        private TextView titleView;
        private ImageView iconView;

        ViewHolder(View view) {
            titleView = view.findViewById(R.id.nav_item_title);
            iconView = view.findViewById(R.id.nav_item_icon);
        }

        void bind(DrawerItem drawerItem) {
            titleView.setText(drawerItem.getTitle());
            iconView.setImageResource(drawerItem.getIcon());
        }
    }
}
