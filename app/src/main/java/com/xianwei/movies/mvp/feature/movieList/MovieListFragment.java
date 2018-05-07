package com.xianwei.movies.mvp.feature.movieList;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.xianwei.movies.R;
import com.xianwei.movies.model.Movie;
import com.xianwei.movies.mvp.BaseFragment;
import com.xianwei.movies.mvp.di.component.DaggerMovieListComponent;
import com.xianwei.movies.mvp.di.module.MovieListModule;
import com.xianwei.movies.mvp.utils.MyApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xianweili on 4/22/18.
 */

public class MovieListFragment extends BaseFragment implements MovieListContract.MovieListView {

    @Inject
    MovieListPresenter movieListPresenter;
    @Inject
    GridLayoutManager gridLayoutManager;
    @Inject
    MovieListAdapter adapter;

    @BindView(R.id.movie_recyclerlistview)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    private static final String EXTRA_TITLE = "title";
    private String title;

    public static MovieListFragment newInstance(String loadingTitle) {

        Bundle args = new Bundle();
        args.putString(EXTRA_TITLE, loadingTitle);

        MovieListFragment fragment = new MovieListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(EXTRA_TITLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_movie_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setUpDagger() {
        DaggerMovieListComponent.builder()
                .appComponent(((MyApplication) getBaseActivity().getApplication()).getAppComponent())
                .movieListModule(new MovieListModule(getBaseActivity()))
                .build()
                .inject(this);
    }

    @Override
    public void setUpPresenter() {
        movieListPresenter.attachView(this);
        movieListPresenter.fetchMovieList(title);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void setCallbackListener(Context context) {

    }

    @Override
    public void onListLoading() {
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListFound() {
        progressBar.setVisibility(View.INVISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onListNotFound(String message) {

    }

    @Override
    public void setRecyclerView(List<Movie> movieList) {
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.addAllToList(movieList);
    }

    @Override
    public void openDetailUI(int position) {
        Toast.makeText(getBaseActivity(), "open new activity", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        movieListPresenter.onDetachView(this);
        super.onDestroyView();
    }
}
