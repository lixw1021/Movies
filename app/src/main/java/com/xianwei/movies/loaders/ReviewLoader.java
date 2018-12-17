package com.xianwei.movies.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.xianwei.movies.R;
import com.xianwei.movies.retrofit.ApiClient;
import com.xianwei.movies.data.ApiService;
import com.xianwei.movies.model.ReviewResponse;
import com.xianwei.movies.model.Review;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

/**
 * Created by xianwei li on 9/8/2017.
 */

public class ReviewLoader extends AsyncTaskLoader<List<Review>> {
    private List<Review> reviews;
    private int movieId;

    public ReviewLoader(Context context, int movieId) {
        super(context);
        this.movieId = movieId;
    }

    @Override
    protected void onStartLoading() {
        if (reviews == null) {
            forceLoad();
        }
    }

    @Override
    public List<Review> loadInBackground() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ReviewResponse> call = apiService.getMovieReviews(movieId, getContext().getString(R.string.api_key));
        List<Review> result = new ArrayList<>();
        try {
            result = call.execute().body().getResults();
        } catch (IOException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public void deliverResult(List<Review> data) {
        reviews = data;
        super.deliverResult(data);
    }
}
