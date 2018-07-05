package me.cristiangomez.news.data.local.database;

import android.provider.BaseColumns;

public final class NewsContract {
    private NewsContract() {

    }

    public static class Story implements BaseColumns {
        public static final String TABLE_NAME = "story";
        public static final String COLUMN_NAME_WEB_URL = "web_url";
        public static final String COLUMN_NAME_API_URL = "api_url";
        public static final String COLUMN_NAME_TRAIL_TEXT = "trail_text";
        public static final String COLUMN_NAME_HEAD_LINE = "head_line";
        public static final String COLUMN_NAME_LAST_MODIFIED = "last_modified";
        public static final String COLUMN_NAME_thumbnail = "thumbnail";
        public static final String COLUMN_NAME_BYLINE = "byline";
        public static final String COLUMN_NAME_BODY = "body";
    }
}
