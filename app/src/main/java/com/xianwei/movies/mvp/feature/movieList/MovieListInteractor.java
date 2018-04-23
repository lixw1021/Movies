package com.xianwei.movies.mvp.feature.movieList;

import com.xianwei.movies.model.Movie;
import com.xianwei.movies.model.MoviesResponse;
import com.xianwei.movies.mvp.data.network.MovieService;
import com.xianwei.movies.mvp.factory.ServiceGeneratorFactory;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by xianweili on 4/22/18.
 */

public class MovieListInteractor {
    @Inject
    ServiceGeneratorFactory serviceGeneratorFactory;
    private CompositeDisposable disposable;
    private OnMovieResponseCallback callback;

    @Inject
    public MovieListInteractor() {
        this.disposable = new CompositeDisposable();
    }

    protected void setCallback(OnMovieResponseCallback callback) {
        this.callback = callback;
    }

    protected void getMovieList(String movieTitle) {
        Observable<MoviesResponse> getMovieListApi =
                serviceGeneratorFactory
                        .createService(MovieService.class).getMovies(movieTitle, "9d2bf28fa6d0d012175b828880e084d9");

        disposable.add(
                getMovieListApi
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(moviesResponse -> {
                            callback.onMovieListRetrieved(moviesResponse.getResults());
                        }, throwable -> {
                            callback.onMovieResponseError(throwable.getMessage());
                        }));
    }

    public void destroyResource() {
        if (!disposable.isDisposed()) {
            disposable.dispose();
        }
        unregisterCallback();
    }

    private void unregisterCallback() {
        callback = null;
    }

    public interface OnMovieResponseCallback {
        void onMovieListRetrieved(List<Movie> movieList);

        void onMovieResponseError(String message);
    }
}
