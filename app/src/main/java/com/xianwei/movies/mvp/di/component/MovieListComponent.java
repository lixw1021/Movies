package com.xianwei.movies.mvp.di.component;

import com.xianwei.movies.mvp.di.module.MovieListModule;
import com.xianwei.movies.mvp.di.scope.MovieListScope;
import com.xianwei.movies.mvp.feature.movieList.MovieListFragment;

import dagger.Component;

/**
 * Created by xianweili on 4/22/18.
 */

@MovieListScope
@Component(modules = MovieListModule.class, dependencies = AppComponent.class)
public interface MovieListComponent {
    void inject(MovieListFragment target);
}
