package com.charlye934.newsjetpack.presenter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.newsjetpa.R
import com.charlye934.newsjetpa.databinding.FragmentNewsBinding
import com.charlye934.newsjetpack.MainActivity
import com.charlye934.newsjetpack.presenter.viewmodel.NewsViewModel
import com.charlye934.newsjetpack.util.Resource

class NewsFragment : Fragment() {

    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var fragmentNewsBinding: FragmentNewsBinding
    private val country = "mx"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentNewsBinding = FragmentNewsBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter

        onClickLister()
        initRecycerView()
        viewNewsList()
    }

    private fun onClickLister(){
        newsAdapter.setOnItemClickListener {
            Toast.makeText(context, "Le dio click", Toast.LENGTH_SHORT).show()
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }

            findNavController().navigate(R.id.action_newsFragment_to_infoFragment, bundle)
        }
    }

    private fun viewNewsList() {
        viewModel.getNewHeadLines(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner, { response ->
            when(response){
                is Resource.Success ->{
                    hideProgresBar()
                    response.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                        Log.d("__totalResult", it.totalResults.toString())
                        Log.d("__totalResult%", (it.totalResults%20).toString())

                        if(it.totalResults == 20){
                            pages = it.totalResults / 20
                        }else{
                            pages = it.totalResults/20+1
                        }
                        isLastPage = page == pages

                    }
                }
                is Resource.Error ->{
                    hideProgresBar()
                    response.message?.let {
                        Toast.makeText(context, "An error ocurred: $it",Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }

    private fun initRecycerView() {
        fragmentNewsBinding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsFragment.onScrollListener())
        }
    }

    private fun showProgressBar(){
        fragmentNewsBinding.progresBar.visibility = View.VISIBLE
    }

    private fun hideProgresBar(){
        fragmentNewsBinding.progresBar.visibility = View.GONE
    }

    private fun onScrollListener () = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                isScrolling = true
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = fragmentNewsBinding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if(shouldPaginate){
                page++

                viewModel.getNewHeadLines(country,page)
                isScrolling = false
            }
        }
    }


}