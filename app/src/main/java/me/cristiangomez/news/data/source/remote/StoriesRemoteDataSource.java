package me.cristiangomez.news.data.source.remote;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.JsonReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import androidx.annotation.NonNull;
import me.cristiangomez.news.BuildConfig;
import me.cristiangomez.news.data.source.StoriesDataSource;

public class StoriesRemoteDataSource implements StoriesDataSource {
    private static StoriesRemoteDataSource instance;

    private StoriesRemoteDataSource() {

    }

    @Override
    public void getStories(@NonNull LoadStoriesCallback callback) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection urlConnection = null;
                try {
                    urlConnection = (HttpURLConnection) new URL(BuildConfig.API_BASE_URL).openConnection();
                    InputStream inputStream = urlConnection.getInputStream();
                    ByteArrayOutputStream result = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) != -1) {
                        result.write(buffer, 0, length);
                    }
                    String resultStr = result.toString("UTF-8");

                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }
            }
        });
    }

    public static StoriesRemoteDataSource getIsntance() {
        if (instance == null) {
            instance = new StoriesRemoteDataSource();
        }
        return instance;
    }
}
