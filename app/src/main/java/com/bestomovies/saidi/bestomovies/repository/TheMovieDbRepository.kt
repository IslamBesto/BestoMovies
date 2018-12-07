package com.bestomovies.saidi.bestomovies.repository

import androidx.lifecycle.LiveData
import com.bestomovies.saidi.bestomovies.data.db.entity.Movie
import com.bestomovies.saidi.bestomovies.data.db.entity.MovieType

interface TheMovieDbRepository {
    suspend fun fetchMovies(type: MovieType): LiveData<List<Movie>>
}