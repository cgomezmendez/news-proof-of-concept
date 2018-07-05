package me.cristiangomez.news.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ImageDownloader {

    /***
     * Downloads image from url and returns a bitmap instance
     * @param imageUri
     * @return
     * @throws IOException
     */
    public static Bitmap downloadImage(Uri imageUri) throws IOException {
        Bitmap bitmap;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(imageUri.toString())
                .openConnection();
        try {
            InputStream inputStream = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } finally {
            httpURLConnection.disconnect();
        }
        return bitmap;
    }
}
