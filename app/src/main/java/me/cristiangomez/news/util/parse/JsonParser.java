package me.cristiangomez.news.util.parse;

import android.util.JsonReader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;

public abstract class JsonParser<T> {
    public abstract T parseJsonFromString(String jsonStringSource) throws JSONException;
}
