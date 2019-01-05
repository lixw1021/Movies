package com.xianwei.movies.data

import com.xianwei.movies.model.MoviesResponse
import com.xianwei.movies.model.ReviewResponse
import com.xianwei.movies.model.TrailerResponse
import io.reactivex.Observable

class MovieRepository: MovieDataContract.MovieRepository {
    override fun saveMovies(response: MoviesResponse): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovies(title: String): Observable<MoviesResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovieVideo(id: Int): Observable<TrailerResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMovieReviews(id: Int): Observable<ReviewResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}