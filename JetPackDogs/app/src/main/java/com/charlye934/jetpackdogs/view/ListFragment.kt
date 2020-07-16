package com.charlye934.jetpackdogs.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bancoazteca.dogsjetpack.viewmodel.ListViewModel
import com.charlye934.jetpackdogs.R
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by activityViewModels()
    private var dogsListAdapter = DoglistAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refresh()

        dogList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.dogs.observe( viewLifecycleOwner, Observer { dogs ->
            dogs?.let {
                dogList.visibility = View.VISIBLE
                dogsListAdapter.updateDogsList(dogs)
            }
        })

        viewModel.dogsLoadError.observe(viewLifecycleOwner, Observer {  isError ->
            isError?.let {
                txtErrorListFragment.visibility = View.VISIBLE
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { isLoading ->
            isLoading?.let {
                loadingViewListFragment.visibility = if (it) View.VISIBLE else View.GONE
                if(it){
                    txtErrorListFragment.visibility = View.GONE
                    dogList.visibility = View.GONE
                }
            }
        })
    }
}