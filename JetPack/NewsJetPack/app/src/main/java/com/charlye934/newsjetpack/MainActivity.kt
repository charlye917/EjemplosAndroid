package com.charlye934.newsjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.charlye934.newsjetpa.R
import com.charlye934.newsjetpa.databinding.ActivityMainBinding
import com.charlye934.newsjetpack.presenter.fragment.NewsAdapter
import com.charlye934.newsjetpack.presenter.viewmodel.NewsViewModel
import com.charlye934.newsjetpack.presenter.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewsViewModelFactory
    @Inject
    lateinit var newsAdapter: NewsAdapter
    lateinit var viewModel: NewsViewModel
    private lateinit var bindign: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindign = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindign.root)

        bindign.bnvNews.setupWithNavController(
            fragment.findNavController()
        )

        viewModel = ViewModelProvider(this, factory)
            .get(NewsViewModel::class.java)
    }
}