package com.xianwei.movies.data

import com.xianwei.movies.model.MoviesResponse
import com.xianwei.movies.model.ReviewResponse
import com.xianwei.movies.model.TrailerResponse
import io.reactivex.Observable

interface MovieDataContract {
    interface Repository {
        fun saveMovies(response: MoviesResponse): Boolean
        fun getMovies(title: String): Observable<MoviesResponse>
        fun getMovieVideo(id: Int): Observable<TrailerResponse>
        fun getMovieReviews(id: Int): Observable<ReviewResponse>
    }

    interface RemoteDataSource {
        fun getRemoteMovies(title: String): Observable<MoviesResponse>
        fun getRemoteMovieVideo(id: Int): Observable<TrailerResponse>
        fun getRemoteMovieReviews(id: Int): Observable<ReviewResponse>
    }

    interface LocalDataSource {
        fun saveMovies(response: MoviesResponse): Boolean
        fun getLocalMovies(title: String): Observable<MoviesResponse>
        fun getLocalMovieVideo(id: Int): Observable<TrailerResponse>
        fun getLocalMovieReviews(id: Int): Observable<ReviewResponse>
    }
}