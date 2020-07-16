package com.bancoazteca.dogsjetpack.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.bancoazteca.dogsjetpack.R
import com.bancoazteca.dogsjetpack.viewmodel.ListViewModel

class ListFragment : Fragment() {

    private var viewModel: ListViewModel by activityViewModels()
    private var dogsListAdapter = DoglistAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}