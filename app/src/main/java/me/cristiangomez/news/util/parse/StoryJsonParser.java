package me.cristiangomez.news.util.parse;

import android.net.Uri;

import org.json.JSONException;
import org.json.JSONObject;

import me.cristiangomez.news.data.Story;

public class StoryJsonParser implements JsonParser<Story> {

    @Override
    /**
     * I could use reflection here to get the fields but as we know then before hand we can save some
     * performance and avoid using reflection
     */
    public Story parseJsonFromString(String jsonStringSource) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStringSource);
        return parseJson(jsonObject);
    }

    @Override
    public Story parseJson(JSONObject jsonObject) throws JSONException {
        JSONObject fieldsJsonObj = jsonObject.getJSONObject("fields");
        Story story = new Story();
        story.setId(jsonObject.getString("id"));
        story.setApiUrl(Uri.parse(jsonObject.getString("apiUrl")));
        story.setByline(fieldsJsonObj.getString("byline"));
//        story.setHeadLine(jsonObject.getString("headline"));
        story.setLastModified(fieldsJsonObj.getString("lastModified"));
        story.setThumbnail(Uri.parse(fieldsJsonObj.getString("thumbnail")));
//        story.setBody(jsonObject.getString("body"));
        return story;
    }
}
