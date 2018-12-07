package com.bestomovies.saidi.bestomovies.ui.movies.list.popular

import androidx.lifecycle.ViewModel
import com.bestomovies.saidi.bestomovies.data.db.entity.MovieType
import com.bestomovies.saidi.bestomovies.repository.TheMovieDbRepository
import com.bestomovies.saidi.bestomovies.utils.lazyDeferred

class PopularMoviesViewModel(private val movieDbRepository: TheMovieDbRepository) : ViewModel() {
    val popular by lazyDeferred {
        movieDbRepository.fetchMovies(MovieType.POPULAR)
    }
}
