package com.xianwei.movies.mvp.utils;

import android.app.Application;

import com.xianwei.movies.mvp.di.component.AppComponent;

/**
 * Created by xianweili on 4/19/18.
 */

public class MyApplication extends Application {

    private static MyApplication sInstance = null;
    private AppComponent appComponent;
}
