package com.bestomovies.saidi.bestomovies.ui.movies.list.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bestomovies.saidi.bestomovies.repository.TheMovieDbRepository

class PopularMoviesViewModelFactory(private val movieDbRepository: TheMovieDbRepository)
    : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(movieDbRepository) as T
    }
}