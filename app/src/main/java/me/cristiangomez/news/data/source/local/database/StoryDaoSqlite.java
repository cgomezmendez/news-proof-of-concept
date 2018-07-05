package me.cristiangomez.news.data.source.local.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.data.source.local.StoryDao;

public class StoryDaoSqlite implements StoryDao {
    NewsDbHelper dbHelper;

    StoryDaoSqlite(Context context) {
        dbHelper = new NewsDbHelper(context);
    }

    @Override
    public List<Story> getStories() {
        return null;
    }

    @Override
    public Story getStoryById(String id) {
        return null;
    }

    @Override
    public void insertStory(Story story) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NewsContract.Story._ID, story.getId());
        values.put(NewsContract.Story.COLUMN_NAME_API_URL, story.getApiUrl().toString());
        db.insert(NewsContract.Story.TABLE_NAME, null, values);
    }
}
