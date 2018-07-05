package me.cristiangomez.news.data.source.remote;

import androidx.annotation.NonNull;
import me.cristiangomez.news.data.source.StoriesDataSource;

public class StoriesRemoteDataSource implements StoriesDataSource {
    private static StoriesRemoteDataSource instance;

    private StoriesRemoteDataSource() {

    }

    @Override
    public void getStories(@NonNull LoadStoriesCallback callback) {
        
    }
}
