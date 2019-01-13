package com.xianwei.movies.mvp.data.network;

import com.xianwei.movies.model.MoviesResponse;
import com.xianwei.movies.model.ReviewResponse;
import com.xianwei.movies.model.TrailerResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by xianweili on 4/18/18.
 */

public interface MovieService {
    @GET("movie/{title}")
    Observable<MoviesResponse> getMovies(@Path("title") String title, @Query("api_key") String apiKey);

    @GET("movie/{id}/videos")
    Observable<TrailerResponse> getMovieVideos(@Path("id") int id, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Observable<ReviewResponse> getMovieReviews(@Path("id") int id, @Query("api_key") String apiKey);
}
