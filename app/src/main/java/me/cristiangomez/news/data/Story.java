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
    String body;

    public Story() {
    }

    public Story(String id, Uri webUrl, Uri apiUrl, String trailText, String headLine,
                 String lastModified, Uri thumbnail, String byline, String body) {
        this.id = id;
        this.webUrl = webUrl;
        this.apiUrl = apiUrl;
        this.trailText = trailText;
        this.headLine = headLine;
        this.lastModified = lastModified;
        this.thumbnail = thumbnail;
        this.byline = byline;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(Uri webUrl) {
        this.webUrl = webUrl;
    }

    public Uri getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(Uri apiUrl) {
        this.apiUrl = apiUrl;
    }

    public String getTrailText() {
        return trailText;
    }

    public void setTrailText(String trailText) {
        this.trailText = trailText;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public Uri getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Uri thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getByline() {
        return byline;
    }

    public void setByline(String byline) {
        this.byline = byline;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
