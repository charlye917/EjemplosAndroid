package com.charlye934.moviesjetpack.presentation.fragment.movie

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.moviesjetpack.R
import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.databinding.FragmentMovieBinding
import com.charlye934.moviesjetpack.presentation.di.Injector
import com.charlye934.moviesjetpack.presentation.viewmodel.movie.MovieViewModel
import com.charlye934.moviesjetpack.presentation.viewmodel.movie.MovieViewModelFactory
import javax.inject.Inject

class MovieFragment : Fragment() {

    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var binding: FragmentMovieBinding
    private lateinit var adapterMovie: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProvider(this, factory)
            .get(MovieViewModel::class.java)

        initRecyclerView()
    }

    private fun initRecyclerView(){
        adapterMovie = MovieAdapter()
        binding.movieRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterMovie
        }

        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        binding.movieProgress.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.getMovies()
        responseLiveData.observe(this, Observer {
            if(it != null){
                Log.d("MYTAG",it.toString())
                adapterMovie.setList(it)
                binding.movieProgress.visibility = View.GONE
            }else{
                binding.movieProgress.visibility = View.GONE
                Toast.makeText(context, "Problemas al cargar", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateMovies(){
        binding.movieProgress.visibility = View.VISIBLE
        val responseLiveData = movieViewModel.updateMovies()
        responseLiveData.observe(this, Observer {
            if(it != null){
                adapterMovie.setList(it)
                binding.movieProgress.visibility = View.GONE
            }else{
                binding.movieRecyclerView.visibility = View.GONE
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_update ->{
                updateMovies()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as Injector).createMovieSubComponent()
            .inject(this)
    }

    companion object {
        val TAG: String = MovieFragment::class.java.simpleName
    }

}