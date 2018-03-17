package com.xianwei.movies.mvp;

/**
 * Created by xianwei li on 3/17/2018.
 */

public interface BaseView {
    void setUpDagger();

    void setUpPresenter();

    void onError(Throwable throwable);

    void hideKeyboard();
}
