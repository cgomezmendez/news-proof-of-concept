package me.cristiangomez.news.util;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.IOException;

public class ImageDownloadTask extends AsyncTask<Uri, Void, Bitmap> {
    ImageDownloadCallback callback;

    public ImageDownloadTask(ImageDownloadCallback callback) {
        this.callback = callback;
    }

    @Override
    protected Bitmap doInBackground(Uri... uris) {
        Bitmap bitmap = null;

        try {
            bitmap = ImageDownloader.downloadImage(uris[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        callback.onImageDownloaded(bitmap);
    }

    public interface ImageDownloadCallback {
        void onImageDownloaded(Bitmap bitmap);
    }
}
