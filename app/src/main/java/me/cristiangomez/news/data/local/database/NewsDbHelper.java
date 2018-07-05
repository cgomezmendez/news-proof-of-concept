package me.cristiangomez.news.data.local.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import me.cristiangomez.news.data.local.database.NewsContract.Story;

public class NewsDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "News.db";
    private static final String SQL_CREATE_STORIES = "CREATE TABLE " +
            Story.TABLE_NAME + " (" + Story._ID + " STRING PRIMARY KEY," +
            Story.COLUMN_NAME_API_URL + " STRING," +
            Story.COLUMN_NAME_BODY + " STRING," +
            Story.COLUMN_NAME_BYLINE + " STRING," +
            Story.COLUMN_NAME_HEAD_LINE + " STRING," +
            Story.COLUMN_NAME_LAST_MODIFIED + " STRING," +
            Story.COLUMN_NAME_thumbnail + " STRING," +
            Story.COLUMN_NAME_TRAIL_TEXT + " STRING," +
            Story.COLUMN_NAME_WEB_URL + "STRING" +
            ")";
    private static final String SQL_DELETE_STORIES = "DROP TABLE IF EXISTS " + Story.TABLE_NAME;

    public NewsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_STORIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_STORIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
