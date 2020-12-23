package com.charlye934.jetpackdogs.presenter.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.jetpackdogs.R
import com.charlye934.jetpackdogs.presenter.adapter.DogListAdapter
import com.charlye934.jetpackdogs.presenter.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import java.util.*

class ListFragment : Fragment() {

    private val listViewModel: ListViewModel by activityViewModels()
    private var dogListAdapter = DogListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerDogList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogListAdapter
        }

        listViewModel.refresh().observe(viewLifecycleOwner){
            dogListAdapter.updateDogList(it)
        }

    }
}