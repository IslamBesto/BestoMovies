package com.bestomovies.saidi.bestomovies.repository

import com.bestomovies.saidi.bestomovies.data.db.entity.Movie

interface TheMovieDbRepository {
    fun getPopularMovies(): LiveData<List<Movie>>
    suspend fun getTopRatedMovues(): LiveData<List<Movie>>
    suspend fun getUpcomingMovies(): LiveData<List<Movie>>
}