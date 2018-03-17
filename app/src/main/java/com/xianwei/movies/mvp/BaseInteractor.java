package com.xianwei.movies.mvp;

/**
 * Created by xianwei li on 3/17/2018.
 */

public interface BaseInteractor {
    void onError(Throwable throwable);

    void clear();
}
