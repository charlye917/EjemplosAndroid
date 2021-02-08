package com.charlye934.moviesjetpack.presentation.fragment.tvshow

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.moviesjetpack.R
import com.charlye934.moviesjetpack.databinding.FragmentTvShowBinding
import com.charlye934.moviesjetpack.presentation.di.Injector
import com.charlye934.moviesjetpack.presentation.viewmodel.tvshow.TvShowViewModel
import com.charlye934.moviesjetpack.presentation.viewmodel.tvshow.TvShowViewModelFactory
import javax.inject.Inject

class TvShowFragment : Fragment() {

    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var tvShowViewModel: TvShowViewModel
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var adapterTvShow: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv_show, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowViewModel = ViewModelProvider(this, factory)
                .get(TvShowViewModel::class.java)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapterTvShow = TvShowAdapter()
        binding.tvRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterTvShow
        }

        displayPopularMovies()
    }

    private fun displayPopularMovies() {
        binding.tvProgressBar.visibility = View.VISIBLE
        val responseLiveData = tvShowViewModel.getTvShow()
        responseLiveData.observe(this, Observer {
            if(it != null){
                adapterTvShow.setData(it)
                binding.tvProgressBar.visibility = View.GONE
            }else{
                binding.tvProgressBar.visibility = View.GONE
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as Injector).createTvShowSubcomponent()
                .inject(this)
    }

    companion object {
       val TAG = TvShowFragment::class.java.simpleName
    }
}