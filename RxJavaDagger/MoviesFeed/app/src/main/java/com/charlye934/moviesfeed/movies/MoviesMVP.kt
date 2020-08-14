package com.charlye934.moviesfeed.movies

import io.reactivex.rxjava3.core.Observable


interface MoviesMVP {

    interface View{
        fun updateData(viewmodel: ViewModel)
        fun showSnackBar(s:String)
    }

    interface Presenter{
        fun loadData()
        fun rxJavaUnsuscribe()
        fun setView(view: MoviesMVP.View)
    }

    interface Model{
        fun result(): Observable<ViewModel>
    }
}