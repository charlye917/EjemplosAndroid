package com.charlye934.moviesfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.charlye934.moviesfeed.movies.MoviesMVP
import com.charlye934.moviesfeed.movies.ViewModel
import com.charlye934.moviesfeed.root.App
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MoviesMVP.View{

    private val TAG = MainActivity::class.simpleName

    @Inject
    lateinit var presenter: MoviesMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponet().inject(this)
    }

    override fun updateData(viewmodel: ViewModel) {

    }

    override fun showSnackBar(s: String) {

    }

    override fun onResume() {
        super.onResume()

        presenter.setView(this)
        presenter.loadData()
    }
}