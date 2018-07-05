package me.cristiangomez.news.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;

import me.cristiangomez.news.data.Image;

public class ImageDownloadTask extends AsyncTask<Uri, Void, Image> {
    ImageDownloadCallback callback;
    ImageCache imageCache;

    public ImageDownloadTask(ImageDownloadCallback callback) {
        this.callback = callback;
        imageCache = ImageCache.getInstance();
    }

    @Override
    protected Image doInBackground(Uri... uris) {
        Image image = new Image();
        image.setUri(uris[0]);
        image.setBitmap(imageCache.getBitmap(image.getUri().toString()));
        if (image.getBitmap() == null) {
            try {
                image.setBitmap(ImageDownloader.downloadImage(image.getUri()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    @Override
    protected void onPostExecute(Image image) {
        super.onPostExecute(image);
        imageCache.addToCache(image.getUri().toString(), image.getBitmap());
        callback.onImageDownloaded(image);
    }

    public interface ImageDownloadCallback {
        void onImageDownloaded(Image image);
    }
}
