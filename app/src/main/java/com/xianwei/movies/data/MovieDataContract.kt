package com.xianwei.movies.data

import com.xianwei.movies.model.MoviesResponse
import com.xianwei.movies.model.ReviewResponse
import com.xianwei.movies.model.TrailerResponse
import io.reactivex.Observer

interface MovieDataContract {
    interface Repository {
        fun saveMovies(response: MoviesResponse): Boolean
        fun getMovies(title: String): Observer<MoviesResponse>
        fun getMovieVideo(id: Int): Observer<TrailerResponse>
        fun getMovieReviews(id: Int): Observer<ReviewResponse>
    }

    interface RemoteDataSource {
        fun getRemoteMovies(title: String): Observer<MoviesResponse>
        fun getRemoteMovieVideo(id: Int): Observer<TrailerResponse>
        fun getRemoteMovieReviews(id: Int): Observer<ReviewResponse>
    }

    interface LocalDataSource {
        fun saveMovies(response: MoviesResponse): Boolean
        fun getLocalMovies(title: String): Observer<MoviesResponse>
        fun getLocalMovieVideo(id: Int): Observer<TrailerResponse>
        fun getLocalMovieReviews(id: Int): Observer<ReviewResponse>
    }
}