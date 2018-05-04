package com.xianwei.movies.mvp.feature.detailmovie;

import com.xianwei.movies.mvp.BasePresenter;
import com.xianwei.movies.mvp.BaseView;

/**
 * Created by xianweili on 5/3/18.
 */

public interface MovieDetailContract {

    interface MovieDetailView extends BaseView {

    }

    interface MovieDetailPresenter extends BasePresenter<MovieDetailView> {
        void onFavoriteIconClick();

        void onUnfavoriteIconClick();
    }
}
