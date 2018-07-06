package me.cristiangomez.news.feed;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import me.cristiangomez.news.R;
import me.cristiangomez.news.data.Story;
import me.cristiangomez.news.data.StorySection;
import me.cristiangomez.news.story.StoryActivity;
import me.cristiangomez.news.util.EndLessScrollListener;

public class FeedFragment extends Fragment implements FeedContract.View {
    private FeedContract.Presenter presenter;
    private ListView listView;
    private FeedListAdapter listAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View endLessScrollProgressView;
    private View noStoriesView;

    @Override
    public void showStories(final List<Story> stories) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listAdapter.addAll(stories);
                }
            });
        }
    }

    @Override
    public void setPresenter(FeedContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showStoryDetailsUi(String storyId) {
        Intent intent = new Intent(getContext(), StoryActivity.class);
        intent.putExtra(StoryActivity.EXTRA_STORY_ID, storyId);
        startActivity(intent);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.feed_frag, container, false);
        noStoriesView = view.findViewById(R.id.feed_no_stories);
        endLessScrollProgressView = view.findViewById(R.id.feed_endless_progress);
        swipeRefreshLayout = view.findViewById(R.id.feed_swipe_container);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refresh();
            }
        });
        listView = view.findViewById(R.id.feed_list_view);
        listView.setOnScrollListener(new EndLessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemCount) {
                endLessScrollProgressView.setVisibility(View.VISIBLE);
                presenter.loadStories(page);
                return true;
            }
        });
        listAdapter = new FeedListAdapter(requireContext(), new ArrayList<Story>());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (listAdapter != null) {
                    presenter.openStoryDetails(listAdapter.getItem(position));
                }
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void hideLoadingAnimation() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    noStoriesView.setVisibility(View.GONE);
                    swipeRefreshLayout.setRefreshing(false);
                    endLessScrollProgressView.setVisibility(View.INVISIBLE);
                }
            });
        }
    }

    @Override
    public void showRefreshLoadingAnimation() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void showErrorWhileLoading() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(requireContext(), R.string.feed_error_loading, Toast.LENGTH_SHORT).show();
                    if (listAdapter.isEmpty()) {
                        noStoriesView.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }
}
