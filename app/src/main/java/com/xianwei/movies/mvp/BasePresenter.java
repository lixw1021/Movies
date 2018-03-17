package com.xianwei.movies.mvp;

/**
 * Created by xianwei li on 3/17/2018.
 */

public interface BasePresenter<V extends BaseView> {
    void attachView(V view);

    void onDetachView(V view);

    V getView();
}
