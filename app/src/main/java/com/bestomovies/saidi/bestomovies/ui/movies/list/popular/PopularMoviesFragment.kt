package com.bestomovies.saidi.bestomovies.ui.movies.list.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bestomovies.saidi.bestomovies.R
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class PopularMoviesFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: PopularMoviesViewModelFactory by instance()

    private lateinit var viewModel: PopularMoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.popular_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this@PopularMoviesFragment, viewModelFactory).get(PopularMoviesViewModel::class.java)
        viewModel.popular.observe(this, Observer { popular ->
            popular_movie.text = popular[0].title
        })
/*
        val theMovieDbApiService = TheMovieDbApiService(ConnectivityIntercetorImpl(this.context!!))
        val movieNetworkDataSource = MovieNetworkDataSourceImpl(theMovieDbApiService)
        movieNetworkDataSource.downloadedPopularMovies.observe(this, Observer { movies ->
            popular_movie.text = movies.movies[0].title
        })
        GlobalScope.launch(Dispatchers.Main) {
            movieNetworkDataSource.fetchPopular("fr")
        }*/
    }

}
