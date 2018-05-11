package com.xianwei.movies.mvp.feature.detailmovie;

import com.xianwei.movies.R;
import com.xianwei.movies.mvp.BaseActivity;

/**
 * Created by xianweili on 5/3/18.
 */

public class MovieDetailActivity extends BaseActivity implements MovieDetailContract.MovieDetailView {

    @Override
    public void setUpDagger() {

    }

    @Override
    public void setUpPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }
}
