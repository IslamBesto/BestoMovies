package com.bestomovies.saidi.bestomovies.ui.movies.list.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bestomovies.saidi.bestomovies.R
import com.bestomovies.saidi.bestomovies.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.popular_movies_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class PopularMoviesFragment : ScopedFragment(), KodeinAware {

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
        bindUI()
    }

    private fun bindUI() = launch {
        val popularMovies = viewModel.popular.await()
        popularMovies.observe(this@PopularMoviesFragment, Observer {
            if (it == null) return@Observer
            popular_movie.text = it[0].title
        })
    }
}
