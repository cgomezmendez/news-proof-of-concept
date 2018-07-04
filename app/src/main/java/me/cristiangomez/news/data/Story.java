package me.cristiangomez.news.data;

import android.net.Uri;

public class Story {
    String id;
    Uri webUrl;
    Uri apiUrl;
    String trailText;
    String headLine;
    String lastModified;
    Uri thumbnail;
    String byline;

    public Story() {
    }

    public Story(String id, Uri webUrl, Uri apiUrl, String trailText, String headLine, String lastModified, Uri thumbnail, String byline) {
        this.id = id;
        this.webUrl = webUrl;
        this.apiUrl = apiUrl;
        this.trailText = trailText;
        this.headLine = headLine;
        this.lastModified = lastModified;
        this.thumbnail = thumbnail;
        this.byline = byline;
    }
}
