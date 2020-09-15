package com.charlye934.movies.data

import androidx.room.Room
import com.charlye934.movies.app.MyApp
import com.charlye934.movies.data.local.MovieDao
import com.charlye934.movies.data.local.MovieRoomDataBase
import com.charlye934.movies.data.remote.ApiConstants
import com.charlye934.movies.data.remote.MovieApiService
import com.charlye934.movies.data.remote.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    companion object{

        private val movieRoomDataBase = Room.databaseBuilder(
            MyApp.getContext(),
            MovieRoomDataBase::class.java,
            "db_movies"
        ).build()

        //RequestInterceptor: Incluir en la cabecera (URL)  de la peticion el Token
        private val okHttpClienBuilder = OkHttpClient
            .Builder()
            .addInterceptor(RequestInterceptor())
        private val client = okHttpClienBuilder.build()

        private val retrofit = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    fun getMovieRoomDB () = movieRoomDataBase

    fun getMovieApiService(): MovieApiService = retrofit.create(MovieApiService::class.java)

}