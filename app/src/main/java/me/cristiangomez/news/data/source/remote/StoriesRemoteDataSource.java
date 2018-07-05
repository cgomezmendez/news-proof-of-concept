package me.cristiangomez.news.data.source.remote;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.NonNull;
import me.cristiangomez.news.BuildConfig;
import me.cristiangomez.news.data.source.ApiResponseStories;
import me.cristiangomez.news.data.source.StoriesDataSource;
import me.cristiangomez.news.util.parse.ApiResponseJsonParser;

public class StoriesRemoteDataSource implements StoriesDataSource {
    private static StoriesRemoteDataSource instance;

    private StoriesRemoteDataSource() {
    }

    @Override
    public void getStories(@NonNull final LoadStoriesCallback callback, int page) {
        HttpURLConnection urlConnection = null;
        try {
            String url = Uri.parse(BuildConfig.API_BASE_URL)
                    .buildUpon()
                    .appendPath("search")
                    .appendQueryParameter("api-key", BuildConfig.API_KEY)
                    .appendQueryParameter("show-fields", "id,webUrl,apiUrl,trailText,headline,lastModified,thumbnail,byline")
                    .appendQueryParameter("page", page + "")
                    .build()
                    .toString();
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            ApiResponseJsonParser jsonParser = new ApiResponseJsonParser();
            ApiResponseStories apiResponse = jsonParser
                    .parseJson(new JSONObject(result.toString("UTF-8"))
                            .getJSONObject("response"));
            if (apiResponse.getStatus().equalsIgnoreCase("ok")) {
                callback.onStoriesLoaded(apiResponse.getResults());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public static StoriesRemoteDataSource getInstance() {
        if (instance == null) {
            instance = new StoriesRemoteDataSource();
        }
        return instance;
    }
}
