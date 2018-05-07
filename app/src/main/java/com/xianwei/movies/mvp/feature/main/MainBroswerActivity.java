package com.xianwei.movies.mvp.feature.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.xianwei.movies.R;
import com.xianwei.movies.model.Movie;
import com.xianwei.movies.mvp.BaseActivity;
import com.xianwei.movies.mvp.di.component.DaggerMainBroserComponent;
import com.xianwei.movies.mvp.di.module.MainBroswerModule;
import com.xianwei.movies.mvp.feature.movieList.MovieListFragment;
import com.xianwei.movies.mvp.utils.MyApplication;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by xianweili on 4/18/18.
 */

public class MainBroswerActivity extends BaseActivity implements MainContract.MainView {

    @Inject
    MainPresenter mainPresenter;
    @Inject
    MoviePagerAdapter adapter;

    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpDagger();
        setUpPresenter();
        setupViewPager();
        setTabLayout();
    }

    public void setTabLayout() {
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void setUpDagger() {
        DaggerMainBroserComponent.builder()
                .mainBroswerModule(new MainBroswerModule(this, this))
                .build()
                .inject(this);
    }

    @Override
    public void setUpPresenter() {
        mainPresenter.attachView(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.layout_main_broswer;
    }

    @Override
    public void setupFavoriteMovieMenu() {

    }

    @Override
    public void setupViewPager() {
        adapter.addFragment(MovieListFragment.newInstance("now_playing"), "Now Playing");
        adapter.addFragment(MovieListFragment.newInstance("upcoming"), "Upcoming");
        adapter.addFragment(MovieListFragment.newInstance("popular"), "Popular");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void openFavoriteUI() {
        Toast.makeText(getBaseContext(), "favorit movie clicked", Toast.LENGTH_LONG).show();
    }

}
