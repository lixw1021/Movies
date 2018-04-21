package com.xianwei.movies.mvp.di.component;

import com.xianwei.movies.mvp.di.module.AppModule;
import com.xianwei.movies.mvp.di.module.MainBroswerModule;
import com.xianwei.movies.mvp.di.module.NetworkModule;
import com.xianwei.movies.mvp.di.scope.MainBroswerScope;
import com.xianwei.movies.mvp.feature.main.MainBroswerActivity;

import dagger.Component;

/**
 * Created by xianweili on 4/20/18.
 */

@MainBroswerScope
@Component(modules = {MainBroswerModule.class, AppModule.class, NetworkModule.class})
public interface MainBroserComponent {
    void inject(MainBroswerActivity target);
}
