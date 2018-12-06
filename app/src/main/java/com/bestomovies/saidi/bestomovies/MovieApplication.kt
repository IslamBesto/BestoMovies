package com.bestomovies.saidi.bestomovies

import android.app.Application
import com.bestomovies.saidi.bestomovies.data.db.MoviesDataBase
import com.bestomovies.saidi.bestomovies.data.network.*
import com.bestomovies.saidi.bestomovies.repository.TheMovieDbRepository
import com.bestomovies.saidi.bestomovies.repository.TheMovieDbRepositoryImpl
import com.bestomovies.saidi.bestomovies.ui.movies.list.popular.PopularMoviesViewModelFactory
import com.jakewharton.threetenabp.AndroidThreeTen
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MovieApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        // this give us the possibility to use context, services ...
        import(androidXModule(this@MovieApplication))
        bind() from singleton { MoviesDataBase(instance()) }
        bind() from singleton { instance<MoviesDataBase>().upcomingDao() }
        bind<ConnectivityIntercetor>() with singleton { ConnectivityIntercetorImpl(instance()) }
        bind() from singleton { TheMovieDbApiService(instance()) }
        bind<MovieNetworkDataSource>() with singleton { MovieNetworkDataSourceImpl(instance()) }
        bind<TheMovieDbRepository>() with singleton { TheMovieDbRepositoryImpl(instance(), instance()) }
        bind() from provider { PopularMoviesViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
    }
}