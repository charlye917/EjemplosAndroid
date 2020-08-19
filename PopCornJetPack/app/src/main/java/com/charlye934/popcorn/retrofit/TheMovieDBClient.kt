package com.charlye934.popcorn.retrofit

import com.charlye934.popcorn.common.Constantes
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TheMovieDBClient {
    private val theMovieDBService: TheMovieDBService
    private var retrofit: Retrofit

    companion object{
        var instace: TheMovieDBClient? = null
        get(){
            if(field == null){
                instace = TheMovieDBClient()
            }
            return field
        }
    }

    init {
        //Incluir el intercepto que hemos definido
        val okkHttpClient = OkHttpClient.Builder()
        okkHttpClient.addInterceptor(TheMovieDBInteractor())

        val client = okkHttpClient.build()

        //consturi el cliente de retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Constantes.TMDBAPI_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        //Instanciamos el servicio retrofit a partir del objeto retrofit
        theMovieDBService = retrofit.create(TheMovieDBService::class.java)
    }

    fun getTheMovieService() = theMovieDBService
}