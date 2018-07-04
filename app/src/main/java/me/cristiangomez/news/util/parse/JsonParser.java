package me.cristiangomez.news.util.parse;

import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;

public interface JsonParser<T> {
    public abstract T parseJsonFromString(String jsonStringSource) throws JSONException;
    public abstract T parseJson(JSONObject jsonObject) throws JSONException;
}
