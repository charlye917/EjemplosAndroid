package com.charlye934.moviesfeed.movies

import android.widget.Toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class MoviesPresenter(private var model: MoviesMVP.Model) : MoviesMVP.Presenter {

    private lateinit var view:MoviesMVP.View
    private var subscription:Disposable? = null

    override fun loadData() {
        subscription = model.result()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :DisposableObserver<ViewModel>(){
                override fun onComplete() {
                    if(view != null)
                        view.showSnackBar("Informacion descargada con exito")
                }

                override fun onNext(viewModel: ViewModel?) {
                    if(viewModel != null){
                        view.updateData(viewModel)
                    }
                }

                override fun onError(e: Throwable?) {
                    view.showSnackBar("Error al descargar las peliculas ${e!!.printStackTrace()}")
                }

            })

    }

    override fun rxJavaUnsuscribe() {
        if(subscription != null && !subscription!!.isDisposed){
            subscription!!.dispose()
        }
    }

    override fun setView(view: MoviesMVP.View) {
        this.view =  view
    }
}