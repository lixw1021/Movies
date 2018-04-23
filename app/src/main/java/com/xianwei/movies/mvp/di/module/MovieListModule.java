package com.xianwei.movies.mvp.di.module;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.xianwei.movies.mvp.di.scope.MovieListScope;
import com.xianwei.movies.mvp.feature.movieList.MovieListAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xianweili on 4/22/18.
 */

@Module
public class MovieListModule {
    private Context context;

    public MovieListModule(Context context) {
        this.context = context;
    }

    @MovieListScope
    @Provides
    public GridLayoutManager provideGridLayoutManager() {
        return new GridLayoutManager(context, 2);
    }

    @MovieListScope
    @Provides
    public MovieListAdapter provideMovieListAdapter() {
        return new MovieListAdapter(context);
    }
}
