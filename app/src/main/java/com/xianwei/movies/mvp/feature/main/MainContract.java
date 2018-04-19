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
        void onListingsFound(List<Movie> movieList);

        void onListingsNoFound();

        void onListingsError(String message);

        void openDetailUI(int position);

        void setRecyclerView(List<Movie> movieList);
    }

    interface MainPresenter extends BasePresenter<MainView> {

        void fetchMovieList();

        void onItemClicked(int position);
    }
}
