package com.xianwei.movies.mvp.feature.movieList;

import com.xianwei.movies.R;
import com.xianwei.movies.adapters.MovieAdapter;
import com.xianwei.movies.model.Movie;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by xianweili on 4/22/18.
 */

public class MovieListPresenter implements MovieListContract.MovieListPresenter,
        MovieListInteractor.OnMovieResponseCallback {

    private MovieListContract.MovieListView movieListView;
    private MovieListInteractor interactor;

    public MovieListPresenter(MovieListContract.MovieListView movieListView, MovieListInteractor interactor) {
        this.interactor = interactor;
    }

    @Inject
    public MovieListPresenter(MovieListInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void attachView(MovieListContract.MovieListView view) {
        this.movieListView = view;
        interactor.setCallback(this);
    }

    @Override
    public void onDetachView(MovieListContract.MovieListView view) {
        interactor.destroyResource();
        this.movieListView = null;
    }

    @Override
    public MovieListContract.MovieListView getView() {
        return movieListView;
    }

    @Override
    public void fetchMovieList(String title) {
        interactor.getMovieList(title);
    }

    @Override
    public void onItemClicked(int position) {
        movieListView.openDetailUI(position);
    }

/////////////////////// MovieListInteractor.OnMovieResponseCallback ////////////////////////////////
    @Override
    public void onMovieListRetrieved(List<Movie> movieList) {
        movieListView.onListFound();
        movieListView.setRecyclerView(movieList);
    }

    @Override
    public void onMovieResponseError(String message) {

    }
}
