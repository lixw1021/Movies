package com.xianwei.movies.data

import com.xianwei.movies.model.MoviesResponse
import com.xianwei.movies.model.ReviewResponse
import com.xianwei.movies.model.TrailerResponse
import io.reactivex.Observable

class MovieLocalDataSource: MovieDataContract.MovieLocalDataSource {
    override fun saveMovies(response: MoviesResponse): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLocalMovies(title: String): Observable<MoviesResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLocalMovieVideo(id: Int): Observable<TrailerResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLocalMovieReviews(id: Int): Observable<ReviewResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}