package com.xianwei.movies.mvp.di.module;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;

import com.xianwei.movies.adapters.MovieAdapter;
import com.xianwei.movies.mvp.di.scope.MainBroswerScope;
import com.xianwei.movies.mvp.feature.main.MainBroswerActivity;
import com.xianwei.movies.mvp.feature.main.MainContract;
import com.xianwei.movies.mvp.feature.main.MoviePagerAdapter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xianweili on 4/20/18.
 */

@Module
public class MainBroswerModule {
    private AppCompatActivity mContext;
    private MainContract.MainView mainView;

    public MainBroswerModule(AppCompatActivity mContext, MainContract.MainView mainView) {
        this.mContext = mContext;
        this.mainView = mainView;
    }

    @MainBroswerScope
    @Provides
    public MoviePagerAdapter provideMoviePagerAdapter() {
        return new MoviePagerAdapter(((MainBroswerActivity)mContext).getSupportFragmentManager());
    }

}
