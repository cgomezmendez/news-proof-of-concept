package me.cristiangomez.news.story;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import me.cristiangomez.news.R;
import me.cristiangomez.news.data.Story;

public class StoryFragment extends Fragment implements StoryContract.View {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.story_frag, container, false);
    }

    @Override
    public void showStory(Story story) {

    }

    @Override
    public void setPresenter(StoryContract.Presenter presenter) {

    }
}
