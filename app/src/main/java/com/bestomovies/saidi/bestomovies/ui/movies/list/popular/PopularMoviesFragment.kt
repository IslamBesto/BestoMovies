package com.bestomovies.saidi.bestomovies.ui.movies.list.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bestomovies.saidi.bestomovies.R
import com.bestomovies.saidi.bestomovies.data.network.ConnectivityIntercetorImpl
import com.bestomovies.saidi.bestomovies.data.network.MovieNetworkDataSourceImpl
import com.bestomovies.saidi.bestomovies.data.network.TheMovieDbApiService
import kotlinx.android.synthetic.main.popular_movies_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PopularMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = PopularMoviesFragment()
    }

    private lateinit var viewModel: PopularMoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.popular_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel::class.java)
        // TODO: Use the ViewModel

        val theMovieDbApiService = TheMovieDbApiService(ConnectivityIntercetorImpl(this.context!!))
        val movieNetworkDataSource = MovieNetworkDataSourceImpl(theMovieDbApiService)
        movieNetworkDataSource.downloadedPopularMovies.observe(this, Observer { movies ->
            popular_movie.text = movies.movies[0].title
        })
        GlobalScope.launch(Dispatchers.Main) {
            movieNetworkDataSource.fetchPopular("fr")
        }
    }

}
