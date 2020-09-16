package com.charlye934.movies.data.network

sealed class Resource<T> (
    status: Status?,
    data: T? = null,
    message:String?)
{
    class Success<T>(data: T): Resource<T>(Status.SUCCESS, data, null)
    class Loading<T>(data: T?): Resource<T>(Status.LOADING, data, null)
    class Error<T>(message: String, data: T? = null): Resource<T>(Status.ERROR, data, message)
}
