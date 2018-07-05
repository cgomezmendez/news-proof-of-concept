package me.cristiangomez.news.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.LruCache;

import me.cristiangomez.news.data.Image;

public class ImageCache {
    private static ImageCache instance;
    private LruCache<String, Bitmap> memoryCache;

    private ImageCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        memoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static ImageCache getInstance() {
        if (instance == null) {
            instance = new ImageCache();
        }
        return instance;
    }

    public void addToCache(String key, Bitmap bitmap) {
        if (getBitmap(key) == null) {
            memoryCache.put(key, bitmap);
        }
    }

    public Bitmap getBitmap(String key) {
        return memoryCache.get(key);
    }
}
