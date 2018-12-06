package com.bestomovies.saidi.bestomovies.ui.movies.list.popular

import com.bestomovies.saidi.bestomovies.repository.TheMovieDbRepository

class PopularMoviesViewModel(private val movieDbRepository: TheMovieDbRepository) : ViewModel() {
    val popular by lazy { movieDbRepository.getPopularMovies() }
}
