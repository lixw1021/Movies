package com.xianwei.movies.mvp.di.module;

import android.app.Application;

import com.xianwei.movies.mvp.di.scope.AppScope;
import com.xianwei.movies.mvp.utils.MyApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Created by xianweili on 4/19/18.
 */

@Module
public class AppModule {

    MyApplication context;

    public AppModule(MyApplication context) {
        this.context = context;
    }

    @Provides
    @AppScope
    public Application provideApplication() {
        return context;
    }
}
