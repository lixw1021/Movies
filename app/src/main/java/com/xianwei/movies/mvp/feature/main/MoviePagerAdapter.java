package com.xianwei.movies.mvp.feature.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.util.Pair;

import com.xianwei.movies.mvp.feature.movieList.MovieListFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by xianweili on 4/22/18.
 */

public class MoviePagerAdapter extends FragmentPagerAdapter {

    private List<Pair<MovieListFragment, String>> fragments = new ArrayList<>();

    public MoviePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).first;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).second;
    }

    public void addFragment(MovieListFragment fragment, String title) {
        fragments.add(new Pair<>(fragment, title));
    }
}
