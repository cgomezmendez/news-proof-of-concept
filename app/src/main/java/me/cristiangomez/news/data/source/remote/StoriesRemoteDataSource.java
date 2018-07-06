package me.cristiangomez.news.data.source.remote;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import me.cristiangomez.news.BuildConfig;
import me.cristiangomez.news.data.source.ApiResponseStories;
import me.cristiangomez.news.data.source.StoriesDataSource;
import me.cristiangomez.news.util.parse.ApiResponseJsonParser;

public class StoriesRemoteDataSource implements StoriesDataSource {
    private static StoriesRemoteDataSource instance;
    private Uri baseUri = Uri.parse(BuildConfig.API_BASE_URL)
            .buildUpon()
            .appendPath("search")
            .appendQueryParameter("api-key", BuildConfig.API_KEY)
            .build();

    private StoriesRemoteDataSource() {
    }

    @Override
    public void getStories(@NonNull final LoadStoriesCallback callback, int page, String section) {
        Uri.Builder uriBuilder = baseUri.buildUpon()
                .appendQueryParameter("show-fields",
                        "id,webUrl,apiUrl,trailText,headline,lastModified,thumbnail,byline,body")
                .appendQueryParameter("page", page + "");
        if (section != null && !section.isEmpty()) {
            uriBuilder.appendQueryParameter("section", section);
        }
        String url = uriBuilder.toString();
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setConnectTimeout((int)TimeUnit.SECONDS.toMicros(15));
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
            } else {
                callback.onNoDataAvailable();
            }
        } catch (IOException e) {
            callback.onNoDataAvailable();
        } catch (JSONException e) {
            callback.onNoDataAvailable();
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

    @Override
    public void getStory(@NonNull LoadStoryCallback callback, String id) {
        //TODO: Implement logic
    }
}
