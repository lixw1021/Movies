package com.xianwei.movies.mvp.feature.main;

import com.xianwei.movies.R;
import com.xianwei.movies.model.Movie;
import com.xianwei.movies.mvp.BaseActivity;

import java.util.List;

/**
 * Created by xianweili on 4/18/18.
 */

public class MainBroswerActivity extends BaseActivity implements MainContract.MainView {
    @Override
    public void setUpDagger() {

    }

    @Override
    public void setUpPresenter() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void onListingsFound(List<Movie> movieList) {

    }

    @Override
    public void onListingsNoFound() {

    }

    @Override
    public void onListingsError(String message) {

    }

    @Override
    public void openDetailUI(int position) {

    }

    @Override
    public void setRecyclerView(List<Movie> movieList) {

    }
}
