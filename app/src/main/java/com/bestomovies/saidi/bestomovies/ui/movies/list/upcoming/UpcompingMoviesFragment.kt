package com.bestomovies.saidi.bestomovies.ui.movies.list.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bestomovies.saidi.bestomovies.R

class UpcompingMoviesFragment : Fragment() {

    companion object {
        fun newInstance() = UpcompingMoviesFragment()
    }

    private lateinit var viewModel: UpcompingMoviesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.upcomping_movies_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UpcompingMoviesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
