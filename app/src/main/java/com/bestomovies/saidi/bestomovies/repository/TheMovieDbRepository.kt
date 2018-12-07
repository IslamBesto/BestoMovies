package com.bestomovies.saidi.bestomovies.repository

import androidx.lifecycle.LiveData
import com.bestomovies.saidi.bestomovies.data.db.entity.Movie

interface TheMovieDbRepository {
    suspend fun getPopularMovies(): LiveData<List<Movie>>
    suspend fun getTopRatedMovies(): LiveData<List<Movie>>
    suspend fun getUpcomingMovies(): LiveData<List<Movie>>
}