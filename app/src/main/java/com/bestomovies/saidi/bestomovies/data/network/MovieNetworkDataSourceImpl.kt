package com.bestomovies.saidi.bestomovies.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bestomovies.saidi.bestomovies.data.network.response.TheMovieDbApi
import com.bestomovies.saidi.bestomovies.utils.NoConnectivityException

class MovieNetworkDataSourceImpl(private val theMovieDbApiService: TheMovieDbApiService) : MovieNetworkDataSource {
    private val _downloadedTopRatedMovies = MutableLiveData<TheMovieDbApi>()
    override val downloadedTopRatedMovies: LiveData<TheMovieDbApi>
        get() = _downloadedTopRatedMovies

    private val _downloadedPopularMovies = MutableLiveData<TheMovieDbApi>()
    override val downloadedPopularMovies: LiveData<TheMovieDbApi>
        get() = _downloadedPopularMovies

    private val _downloadedUpcomingMovies = MutableLiveData<TheMovieDbApi>()
    override val downloadedUpcomingMovies: LiveData<TheMovieDbApi>
        get() = _downloadedUpcomingMovies

    override suspend fun fetchTopRated(language: String) {
        try {
            val fetchedTopRated = theMovieDbApiService
                    .getTopRatedMovies(language)
                    .await()
            _downloadedTopRatedMovies.postValue(fetchedTopRated)
        } catch (exception: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", exception)
        }
    }

    override suspend fun fetchPopular(language: String) {
        try {
            val fetchedPopular = theMovieDbApiService
                    .getPopularMovies(language)
                    .await()
            _downloadedPopularMovies.postValue(fetchedPopular)
        } catch (exception: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", exception)
        }
    }

    override suspend fun fetchUpComing(language: String) {
        try {
            val fetchedUpcoming = theMovieDbApiService
                    .getUpcomingMovies(language)
                    .await()
            _downloadedUpcomingMovies.postValue(fetchedUpcoming)
        } catch (exception: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", exception)
        }
    }
}