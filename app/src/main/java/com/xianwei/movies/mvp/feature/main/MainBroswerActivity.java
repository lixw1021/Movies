package com.xianwei.movies.mvp.feature.main;

import com.xianwei.movies.model.Movie;

import java.util.List;

/**
 * Created by xianweili on 4/18/18.
 */

public class MainBroswerActivity implements MainContract.MainView {
    @Override
    public void setUpDagger() {

    }

    @Override
    public void setUpPresenter() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void onListingsFound(List<Movie> movieList) {

    }

    @Override
    public void onListingsNoFound() {

    }

    @Override
    public void onListingsError(String message) {

    }

    @Override
    public void openDetailUI(int position) {

    }

    @Override
    public void setRecyclerView(List<Movie> movieList) {

    }
}
