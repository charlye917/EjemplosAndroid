package com.charlye934.popcorn.repository

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.charlye934.popcorn.common.MyApp
import com.charlye934.popcorn.retrofit.TheMovieDBClient
import com.charlye934.popcorn.retrofit.TheMovieDBService
import com.charlye934.popcorn.retrofit.model.Movie
import com.charlye934.popcorn.retrofit.model.PopularMoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TheMovieDBRepository {
    var theMovieDBService: TheMovieDBService? = null
    var theMovieDBClient: TheMovieDBClient? = null
    var popularMovies: MutableLiveData<List<Movie>>? = null

    init {
        theMovieDBClient = TheMovieDBClient.instace
        theMovieDBService = theMovieDBClient?.getTheMovieService()
        popularMovies = popularMovies()
    }

    fun popularMovies(): MutableLiveData<List<Movie>>?{
        if(popularMovies == null){
            popularMovies = MutableLiveData<List<Movie>>()
        }

        val call: Call<PopularMoviesResponse>? = theMovieDBService?.getPopulaarMoves()
        call?.enqueue(object : Callback<PopularMoviesResponse>{
            override fun onFailure(call: Call<PopularMoviesResponse>, t: Throwable) {
                Toast.makeText(MyApp.instance, "Error en la llamada", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PopularMoviesResponse>, response: Response<PopularMoviesResponse>) {
                if(response.isSuccessful){
                    popularMovies?.value = response.body()?.results
                }
            }
        })

        return popularMovies
    }
}