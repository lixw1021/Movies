package com.xianwei.movies.data

import com.xianwei.movies.model.MoviesResponse
import com.xianwei.movies.model.ReviewResponse
import com.xianwei.movies.model.TrailerResponse
import com.xianwei.movies.mvp.data.network.MovieService
import io.reactivex.Observable
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieService: MovieService): MovieDataContract.MovieRemoteDataSource {

    override fun getRemoteMovies(title: String): Observable<MoviesResponse> {
        return movieService.getMovies(title, "key")
    }

    override fun getRemoteMovieVideo(id: Int): Observable<TrailerResponse> {
        return movieService.getMovieVideos(id, "key")
    }

    override fun getRemoteMovieReviews(id: Int): Observable<ReviewResponse> {
        return movieService.getMovieReviews(id, "key")
    }
}