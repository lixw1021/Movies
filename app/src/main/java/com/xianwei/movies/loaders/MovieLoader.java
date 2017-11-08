package com.xianwei.movies.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.xianwei.movies.R;
import com.xianwei.movies.retrofit.ApiClient;
import com.xianwei.movies.retrofit.ApiInterface;
import com.xianwei.movies.model.Movie;
import com.xianwei.movies.model.MoviesResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by xianwei li on 8/29/2017.
 */

public class MovieLoader extends AsyncTaskLoader<List<Movie>> {
    private String urlPath;
    private List<Movie> cacheMovies;

    public MovieLoader(Context context, String urlPath) {
        super(context);
        this.urlPath = urlPath;
    }

    @Override
    protected void onStartLoading() {
        if (cacheMovies == null) {
            forceLoad();
        }
    }

    @Override
    public List<Movie> loadInBackground() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<MoviesResponse> call = apiService.getMovies(urlPath, getContext().getString(R.string.api_key));
        List<Movie> result = new ArrayList<>();
        try {
            result = call.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
//        return QueryUtil.fetchMovies(url);
    }

    @Override
    public void deliverResult(List<Movie> data) {
        cacheMovies = data;
        super.deliverResult(data);
    }
}
