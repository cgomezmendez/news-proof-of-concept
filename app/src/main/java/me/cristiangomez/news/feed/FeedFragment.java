package me.cristiangomez.news.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import me.cristiangomez.news.R;
import me.cristiangomez.news.data.Story;

public class FeedFragment extends Fragment implements FeedContract.View {
    private FeedContract.Presenter presenter;

    @Override
    public void showStories(List<Story> stories) {
        // TODO: implement logic
    }

    @Override
    public void setPresenter(FeedContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showTaskDetailsUi(String taskId) {
        //TODO: Implement logic
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.feed_frag, container, false);
    }
}
