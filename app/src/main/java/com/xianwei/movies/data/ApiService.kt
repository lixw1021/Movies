package com.xianwei.movies.data

import com.xianwei.movies.model.MoviesResponse
import com.xianwei.movies.model.ReviewResponse
import com.xianwei.movies.model.TrailerResponse

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{title}")
    fun getMovies(@Path("title") title: String, @Query("api_key") apiKey: String): Call<MoviesResponse>

    @GET("movie/{id}/videos")
    fun getMovieVideos(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<TrailerResponse>

    @GET("movie/{id}/reviews")
    fun getMovieReviews(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<ReviewResponse>
}
