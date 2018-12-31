package com.xianwei.movies.data

import com.xianwei.movies.model.MoviesResponse
import com.xianwei.movies.model.ReviewResponse
import com.xianwei.movies.model.TrailerResponse
import io.reactivex.Observer

class MovieRemoteDataSource: MovieDataContract.RemoteDataSource {
    override fun getRemoteMovies(title: String): Observer<MoviesResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRemoteMovieVideo(id: Int): Observer<TrailerResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRemoteMovieReviews(id: Int): Observer<ReviewResponse> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}