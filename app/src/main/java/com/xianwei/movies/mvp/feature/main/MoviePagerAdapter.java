package com.xianwei.movies.mvp.feature.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xianwei.movies.mvp.feature.movieList.MovieListFragment;

import javax.inject.Inject;

/**
 * Created by xianweili on 4/22/18.
 */

public class MoviePagerAdapter extends FragmentStatePagerAdapter {

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        MovieListFragment movieListFragment = new MovieListFragment();
        return movieListFragment;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Now Playing";
    }
}
