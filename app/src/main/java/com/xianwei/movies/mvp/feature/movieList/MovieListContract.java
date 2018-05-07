package com.xianwei.movies.mvp.feature.movieList;

import com.xianwei.movies.model.Movie;
import com.xianwei.movies.mvp.BasePresenter;
import com.xianwei.movies.mvp.BaseView;

import java.util.List;

/**
 * Created by xianweili on 4/22/18.
 */

public class MovieListContract {

    interface MovieListView extends BaseView {
        void onListLoading();

        void onListFound ();

        void onListNotFound(String message);

        void setRecyclerView(List<Movie> movieList);

        void openDetailUI(int position);
    }

    interface MovieListPresenter extends BasePresenter<MovieListView> {
        void fetchMovieList(String title);

        void onItemClicked(int position);
    }
}
