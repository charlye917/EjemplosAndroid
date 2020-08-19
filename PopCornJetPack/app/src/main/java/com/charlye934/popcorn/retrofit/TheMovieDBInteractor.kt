package com.charlye934.popcorn.retrofit

import com.charlye934.popcorn.common.Constantes
import okhttp3.Interceptor
import okhttp3.Response

class TheMovieDBInteractor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        //Añadimos parametros a la uRL de la cadena que recibimos
        val urlWithParams = chain.request()
            .url
            .newBuilder()
            .addQueryParameter(Constantes.URL_PARAM_API_KEY, Constantes.API_KEY)
            .addQueryParameter(Constantes.URL_PARAM_LANGUAGE, "es-ES")
            .build()

        val request = chain.request()
            .newBuilder()
            .url(urlWithParams)
            .addHeader("Content-Type","application/json")
            .addHeader("Accept", "application/json")
            .build()

        return chain.proceed(request)
    }
}