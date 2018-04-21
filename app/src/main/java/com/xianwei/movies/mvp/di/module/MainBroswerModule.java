package com.xianwei.movies.mvp.di.module;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.xianwei.movies.adapters.MovieAdapter;
import com.xianwei.movies.mvp.di.scope.MainBroswerScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xianweili on 4/20/18.
 */

@Module
public class MainBroswerModule {
    private Context mContext;

    public MainBroswerModule(Context mContext) {
        this.mContext = mContext;
    }

    @MainBroswerScope
    @Provides
    public GridLayoutManager provideGridLayoutManager() {
        return new GridLayoutManager(mContext, 2);
    }

    @MainBroswerScope
    @Provides
    public MovieAdapter provideMovieAdapter() {
        return new MovieAdapter(mContext, null);
    }

}
