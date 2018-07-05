package me.cristiangomez.news.feed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import me.cristiangomez.news.R;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.util.EndLessScrollListener;

public class FeedFragment extends Fragment implements FeedContract.View {
    private FeedContract.Presenter presenter;
    private ListView listView;
    private FeedListAdapter listAdapter;

    @Override
    public void showStories(final List<Story> stories) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.addAll(stories);
            }
        });
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
        View view = inflater.inflate(R.layout.feed_frag, container, false);
        listView = view.findViewById(R.id.feed_list_view);
        listView.setOnScrollListener(new EndLessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemCount) {
                presenter.loadStories(page);
                return true;
            }
        });
        listAdapter = new FeedListAdapter(requireContext(), new ArrayList<Story>());
        listView.setAdapter(listAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }
}
