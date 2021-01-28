package com.charlye934.moviesjetpack.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.charlye934.moviesjetpack.R
import com.charlye934.moviesjetpack.databinding.FragmentMovieBinding
import com.charlye934.moviesjetpack.presentation.di.Injector
import com.charlye934.moviesjetpack.presentation.viewmodel.movie.MovieViewModel
import com.charlye934.moviesjetpack.presentation.viewmodel.movie.MovieViewModelFactory
import javax.inject.Inject

class MovieFragment : Fragment() {

    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (MovieFragment as Injector).createMovieSubComponent()
            .inject(this)

        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    companion object {
        val TAG: String = MovieFragment::class.java.simpleName
    }

}