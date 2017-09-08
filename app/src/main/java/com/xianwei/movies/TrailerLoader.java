package com.xianwei.movies;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.xianwei.movies.Utils.QueryUtil;

import java.util.List;

/**
 * Created by xianwei li on 9/8/2017.
 */

public class TrailerLoader extends AsyncTaskLoader<List<Trailer>> {
    private String url;
    private List<Trailer> trailerList;

    public TrailerLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        if (trailerList == null) {
            forceLoad();
        }
    }

    @Override
    public List<Trailer> loadInBackground() {
        List<Trailer> result = QueryUtil.fetchTrailerInfo(url);
        return result;
    }

    @Override
    public void deliverResult(List<Trailer> data) {
        trailerList = data;
        super.deliverResult(data);
    }
}
