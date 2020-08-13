package com.charlye934.moviesfeed

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.moviesfeed.movies.MoviesMVP
import com.charlye934.moviesfeed.movies.ViewModel
import com.charlye934.moviesfeed.root.App
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MoviesMVP.View{

    private val TAG = MainActivity::class.simpleName
    private lateinit var listAdapter:ListAdapter
    private var resultList: MutableList<ViewModel> = arrayListOf()

    @Inject
    lateinit var presenter: MoviesMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponet().inject(this)
        listAdapter = ListAdapter(resultList)
        recyclerMovies.apply {
            adapter = listAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity,DividerItemDecoration.HORIZONTAL))
            hasFixedSize()
        }
    }

    override fun updateData(viewmodel: ViewModel) {
        resultList.add(viewmodel)
        listAdapter.notifyItemChanged(resultList.size - 1)
        Log.d(TAG, "Informacion nueva: " + viewmodel.nameMovie)
    }

    override fun showSnackBar(s: String) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        presenter.setView(this)
        presenter.loadData()
    }

    override fun onStop() {
        super.onStop()
        presenter.rxJavaUnsuscribe()
        resultList.clear()
        listAdapter.notifyDataSetChanged()
    }
}