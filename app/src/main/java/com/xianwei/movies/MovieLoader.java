package com.xianwei.movies;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.xianwei.movies.Utils.QueryUtil;

import java.util.List;

/**
 * Created by xianwei li on 8/29/2017.
 */

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {
    private String url;

    public MovieLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Movie> loadInBackground() {
        return QueryUtil.fetchMoviesList(url);
    }
}
