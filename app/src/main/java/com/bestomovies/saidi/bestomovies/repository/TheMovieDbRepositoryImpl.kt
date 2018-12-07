package com.bestomovies.saidi.bestomovies.repository

import androidx.lifecycle.LiveData
import com.bestomovies.saidi.bestomovies.data.db.dao.MovieDao
import com.bestomovies.saidi.bestomovies.data.db.entity.Movie
import com.bestomovies.saidi.bestomovies.data.db.entity.MovieType
import com.bestomovies.saidi.bestomovies.data.network.MovieNetworkDataSource
import com.bestomovies.saidi.bestomovies.data.network.response.TheMovieDbApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.ZonedDateTime

class TheMovieDbRepositoryImpl(private val movieDao: MovieDao,
                               private val movieNetworkDataSource: MovieNetworkDataSource) : TheMovieDbRepository {

    init {
        movieNetworkDataSource.downloadedPopularMovies.observeForever { popularMovies ->
            persistFetchedPopularMovies(popularMovies)
        }

        movieNetworkDataSource.downloadedTopRatedMovies.observeForever { topRatedMovies ->
            persistFetchedTopRatedMovies(topRatedMovies)
        }

        movieNetworkDataSource.downloadedUpcomingMovies.observeForever { upComingMovies ->
            persistFetchedUpcomingMovies(upComingMovies)
        }
    }

    // TODO optimize this
    override suspend fun getPopularMovies(): LiveData<List<Movie>> {
        return withContext(Dispatchers.IO) {
            initPopularMovies()
            return@withContext movieDao.getMovies()
        }
    }

    override suspend fun getTopRatedMovies(): LiveData<List<Movie>> {
        return withContext(Dispatchers.IO) {
            initTopRatedMovies()
            return@withContext movieDao.getMovies()
        }
    }

    override suspend fun getUpcomingMovies(): LiveData<List<Movie>> {
        return withContext(Dispatchers.IO) {
            initUpcomingMovies()
            return@withContext movieDao.getMovies()
        }
    }

    private fun persistFetchedPopularMovies(theMovieDbApi: TheMovieDbApi) {
        GlobalScope.launch(Dispatchers.IO) {
            for (movie in theMovieDbApi.movies) {
                movie.type = MovieType.POPULAR
                movieDao.insertMovie(movie)
            }
        }
    }

    private fun persistFetchedTopRatedMovies(theMovieDbApi: TheMovieDbApi) {
        GlobalScope.launch(Dispatchers.IO) {
            for (movie in theMovieDbApi.movies) {
                movie.type = MovieType.TOP_RATED
                movieDao.insertMovie(movie)
            }
        }
    }

    private fun persistFetchedUpcomingMovies(theMovieDbApi: TheMovieDbApi) {
        GlobalScope.launch(Dispatchers.IO) {
            for (movie in theMovieDbApi.movies) {
                movie.type = MovieType.UPCOMING
                movieDao.insertMovie(movie)
            }
        }
    }

    private suspend fun initPopularMovies() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchPopularMovies()
    }

    private suspend fun initTopRatedMovies() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchTopratedMovies()
    }

    private suspend fun initUpcomingMovies() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchUpcomingMovies()
    }

    private suspend fun fetchPopularMovies() {
        movieNetworkDataSource.fetchPopular("en-US")
    }

    private suspend fun fetchTopratedMovies() {
        movieNetworkDataSource.fetchTopRated("en_US")
    }

    private suspend fun fetchUpcomingMovies() {
        movieNetworkDataSource.fetchUpComing("en-US")
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }
}