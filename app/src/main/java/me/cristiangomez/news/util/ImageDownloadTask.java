package me.cristiangomez.news.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;

import me.cristiangomez.news.data.Image;

public class ImageDownloadTask extends AsyncTask<Uri, Void, Image> {
    ImageDownloadCallback callback;

    public ImageDownloadTask(ImageDownloadCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Image doInBackground(Uri... uris) {
        Image image = new Image();
        image.setUri(uris[0]);
        try {
            image.setBitmap(ImageDownloader.downloadImage(uris[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void onPostExecute(Image image) {
        super.onPostExecute(image);
        callback.onImageDownloaded(image);
    }

    public interface ImageDownloadCallback {
        void onImageDownloaded(Image image);
    }
}
