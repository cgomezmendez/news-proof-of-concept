package me.cristiangomez.news.data;

import android.graphics.Bitmap;
import android.net.Uri;

public class Image {
    Bitmap bitmap;
    Uri uri;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
