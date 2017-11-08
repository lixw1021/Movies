package com.xianwei.movies.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.xianwei.movies.R;
import com.xianwei.movies.retrofit.ApiClient;
import com.xianwei.movies.retrofit.ApiInterface;
import com.xianwei.movies.model.TrailerResponse;
import com.xianwei.movies.model.Trailer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by xianwei li on 9/8/2017.
 */

public class TrailerLoader extends AsyncTaskLoader<List<Trailer>> {
    private int movieId;
    private List<Trailer> trailerList;

    public TrailerLoader(Context context, int movieId) {
        super(context);
        this.movieId = movieId;
    }

    @Override
    protected void onStartLoading() {
        if (trailerList == null) {
            forceLoad();
        }
    }

    @Override
    public List<Trailer> loadInBackground() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TrailerResponse> call = apiService.getMovieVideos(movieId, getContext().getString(R.string.api_key));
        List<Trailer> result = new ArrayList<>();
        try {
            result = call.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public void deliverResult(List<Trailer> data) {
        trailerList = data;
        super.deliverResult(data);
    }
}
