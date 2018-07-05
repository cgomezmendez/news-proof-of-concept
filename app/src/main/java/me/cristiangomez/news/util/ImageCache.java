package me.cristiangomez.news.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.LruCache;

public class ImageCache {
    LruCache<Uri, Bitmap> memoryCache;

    private ImageCache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        final int cacheSize = maxMemory / 8;
        memoryCache = new LruCache<Uri, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(Uri key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static ImageCache getInstance() {
        return new ImageCache();
    }
}
