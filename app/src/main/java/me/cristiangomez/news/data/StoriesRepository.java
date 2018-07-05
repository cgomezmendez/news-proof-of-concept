package me.cristiangomez.news.data;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.source.StoriesDataSource;

public class StoriesRepository implements StoriesDataSource {
    private static StoriesRepository instance;
    private final StoriesDataSource remoteDataSource;
    private final StoriesDataSource localDataSource;

    Map<String, Story> cachedStories;

    boolean isCacheDirty = false;

    private StoriesRepository(StoriesDataSource remoteDataSource, StoriesDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
    }

    public static StoriesRepository getInstance(StoriesDataSource remoteDataSource,
                                                StoriesDataSource localDataSource) {
        if (instance == null) {
            instance = new StoriesRepository(remoteDataSource, localDataSource);
        }
        return instance;
    }

    public static void destroyinstance() {
        instance = null;
    }

    @Override
    public void getStories(@NonNull final LoadStoriesCallback callback, final int page) {
        localDataSource.getStories(new LoadStoriesCallback() {
            @Override
            public void onStoriesLoaded(List<Story> stories) {

            }

            @Override
            public void onNoDataAvailable() {
                getStoriesFromRemoteDataSource(callback, page);
            }
        }, page);
    }

    private void getStoriesFromRemoteDataSource(@NonNull final LoadStoriesCallback callback, int page) {
        remoteDataSource.getStories(new LoadStoriesCallback() {
            @Override
            public void onStoriesLoaded(List<Story> stories) {
                refreshCache(stories);
                callback.onStoriesLoaded(stories);
            }

            @Override
            public void onNoDataAvailable() {
                callback.onNoDataAvailable();
            }
        }, page);
    }

    private void refreshCache(List<Story> stories) {
        if (cachedStories == null) {
            cachedStories = new LinkedHashMap<>();
        }
        for (Story story: stories) {
            cachedStories.put(story.id, story);
        }
        isCacheDirty = false;
    }
}
