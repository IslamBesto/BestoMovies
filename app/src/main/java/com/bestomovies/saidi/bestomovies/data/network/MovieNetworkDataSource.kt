package com.bestomovies.saidi.bestomovies.data.network

import androidx.lifecycle.LiveData
import com.bestomovies.saidi.bestomovies.data.network.response.TheMovieDbApi

interface MovieNetworkDataSource {
    val downloadedTopRatedMovies: LiveData<TheMovieDbApi>
    val downloadedPopularMovies: LiveData<TheMovieDbApi>
    val downloadedUpcomingMovies: LiveData<TheMovieDbApi>

    suspend fun fetchTopRated(language: String)
    suspend fun fetchPopular(language: String)
    suspend fun fetchUpComing(language: String)
}