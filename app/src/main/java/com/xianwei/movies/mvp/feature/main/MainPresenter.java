package com.xianwei.movies.mvp.feature.main;

import javax.inject.Inject;

/**
 * Created by xianweili on 4/18/18.
 */

public class MainPresenter implements MainContract.MainPresenter {
    private MainContract.MainView mainView;

    @Inject
    public MainPresenter() {
    }

    @Override
    public void attachView(MainContract.MainView view) {
        mainView = view;
    }

    @Override
    public void onDetachView(MainContract.MainView view) {
        mainView = null;
    }

    @Override
    public MainContract.MainView getView() {
        return mainView;
    }

    @Override
    public void onFavoriteMovieMenuClick() {

    }
}
