package me.cristiangomez.news.util.parse;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import me.cristiangomez.news.data.ApiResponse;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.data.source.ApiResponseStories;

public class ApiResponseJsonParser implements JsonParser<ApiResponseStories> {
    @Override
    public ApiResponseStories parseJsonFromString(String jsonStringSource) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonStringSource);
        return parseJson(jsonObject);
    }

    @Override
    public ApiResponseStories parseJson(JSONObject jsonObject) throws JSONException {
        ApiResponseStories apiResponseStories = new ApiResponseStories();
        apiResponseStories.setStatus(jsonObject.optString("status"));
        apiResponseStories.setCurrentPage(jsonObject.optInt("currentPage"));
        apiResponseStories.setOrderBy(jsonObject.optString("orderBy"));
        apiResponseStories.setPages(jsonObject.optInt("pages"));
        apiResponseStories.setPageSize(jsonObject.optInt("pageSize"));
        apiResponseStories.setStartIndex(jsonObject.optInt("startIndex"));
        apiResponseStories.setTotal(jsonObject.optInt("total"));
        List<Story> stories = new ArrayList<>();
        StoryJsonParser storyJsonParser = new StoryJsonParser();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                stories.add(storyJsonParser.parseJson(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        apiResponseStories.setResults(stories);
        return apiResponseStories;
    }
}
