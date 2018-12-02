package com.bestomovies.saidi.bestomovies.ui.movies.list.toprated

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bestomovies.saidi.bestomovies.R

class TopRAtedMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = TopRAtedMoviesFragment()
    }

    private lateinit var viewModel: TopRatedMoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.top_rated_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TopRatedMoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
