package com.charlye934.movies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.movies.R
import com.charlye934.movies.data.local.MovieEntity
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private var nColumnCount = 1
    private val adapterMovies = MovieAdapter()
    private var movieList: List<MovieEntity> = arrayListOf()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        recyclerData()
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun recyclerData(){
        listRecycler.apply {
            if(nColumnCount <= 1)
                layoutManager = LinearLayoutManager(context)
            else
                layoutManager = GridLayoutManager(context, nColumnCount)
            adapter = adapterMovies
        }
    }

    companion object{
        fun newInstance() = MovieFragment
    }
}