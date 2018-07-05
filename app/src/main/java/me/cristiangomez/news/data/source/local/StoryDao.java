package me.cristiangomez.news.data.source.local;

import java.util.List;

import me.cristiangomez.news.data.Story;

public interface StoryDao {
    List<Story> getStories();

    Story getStoryById(String id);

    void insertStory(Story story);
}
