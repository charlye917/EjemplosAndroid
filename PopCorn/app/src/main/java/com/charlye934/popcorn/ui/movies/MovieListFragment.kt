package com.charlye934.popcorn.ui.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.popcorn.R
import com.charlye934.popcorn.retrofit.model.Movie

class MovieListFragment : Fragment() {

    private val movieViewModel:MovieViewModel by activityViewModels()
    private lateinit var movieAdapter:MovieRecyclerViewAdapter
    private var popularMovies: List<Movie> = ArrayList()

    private var columnCount = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_movie_list, container, false)

        movieAdapter = MovieRecyclerViewAdapter()

        //set the adapter
        if(view is RecyclerView){
            with(view){
                layoutManager = when{
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else    -> GridLayoutManager(context, columnCount)
                }

                adapter = movieAdapter
            }
        }

        //Obtener las peliculas
        movieViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            popularMovies = it
            movieAdapter.setData(popularMovies)
        })
        return view
    }

    companion object{
        private const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount:Int) =
            MovieListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}