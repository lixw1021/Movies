package com.xianwei.movies.mvp.feature.main;

import com.xianwei.movies.model.Movie;
import com.xianwei.movies.mvp.BasePresenter;
import com.xianwei.movies.mvp.BaseView;

import java.util.List;

/**
 * Created by xianweili on 4/18/18.
 */

public interface MainContract {

    interface MainView extends BaseView {
        void setupFavoriteMovieMenu();

        void setupViewPager();

        void openFavoriteUI();
    }

    interface MainPresenter extends BasePresenter<MainView> {
        void onFavoriteMovieMenuClick();
    }
}
