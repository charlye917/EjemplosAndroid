package com.charlye934.jetpackdogs.presenter.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.jetpackdogs.R
import com.charlye934.jetpackdogs.presenter.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list_dog.*

class ListFragmentDog : Fragment() {
    private val viewModel: ListViewModel by activityViewModels()
    private var dogsListAdapter = DoglistAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_list_dog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refresh()

        dogList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        refreshListFragment.setOnRefreshListener {
            dogList.visibility = View.GONE
            txtErrorListFragment.visibility = View.GONE
            loadingViewListFragment.visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            refreshListFragment.isRefreshing = false
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
                txtErrorListFragment.visibility = if(it) View.VISIBLE else View.GONE
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.actionSettings -> {
                view?.let{Navigation.findNavController(it).navigate(ListFragmentDogDirections.actionSettings()) }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}