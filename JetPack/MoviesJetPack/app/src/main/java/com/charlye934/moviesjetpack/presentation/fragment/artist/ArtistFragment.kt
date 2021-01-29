package com.charlye934.moviesjetpack.presentation.fragment.artist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.charlye934.moviesjetpack.R
import com.charlye934.moviesjetpack.databinding.FragmentArtistBinding
import com.charlye934.moviesjetpack.presentation.di.Injector
import com.charlye934.moviesjetpack.presentation.viewmodel.artist.ArtistViewModel
import com.charlye934.moviesjetpack.presentation.viewmodel.artist.ArtistViewModelFactory
import javax.inject.Inject

class ArtistFragment : Fragment() {

    @Inject
    lateinit var factory: ArtistViewModelFactory
    private lateinit var artistViewModel: ArtistViewModel
    private lateinit var binding: FragmentArtistBinding
    private lateinit var adapterArtist: ArtistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_artist, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artistViewModel = ViewModelProvider(this,factory)
                .get(ArtistViewModel::class.java)

    }

    private fun initRecyclerView(){
        adapterArtist = ArtistAdapter()
        binding.artistRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterArtist
        }

        displayPopularArtist()
    }

    private fun displayPopularArtist() {
        binding.artistProgresBar.visibility = View.VISIBLE
        val responseLiveData = artistViewModel.getArtist()
        responseLiveData.observe(this, Observer {
            if(it != null){
                adapterArtist.setList(it)
                binding.artistProgresBar.visibility = View.GONE
            }else{
                binding.artistProgresBar.visibility = View.GONE
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (context.applicationContext as Injector).createArtistSubComponent()
            .inject(this)
    }

    companion object {
        val TAG = ArtistFragment::class.java.simpleName
    }
}