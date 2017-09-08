package com.xianwei.movies.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.xianwei.movies.Utils.QueryUtil;

import java.util.List;

/**
 * Created by xianwei li on 9/8/2017.
 */

public class ReviewLoader extends AsyncTaskLoader<List<String>> {
    private List<String> reviews;
    private String url;

    public ReviewLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        if (reviews == null) {
            forceLoad();
        }
    }

    @Override
    public List<String> loadInBackground() {
        return QueryUtil.fetchReviews(url);
    }

    @Override
    public void deliverResult(List<String> data) {
        reviews = data;
        super.deliverResult(data);
    }
}
