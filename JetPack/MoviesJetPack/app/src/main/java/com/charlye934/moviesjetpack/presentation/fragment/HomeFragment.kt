package com.charlye934.moviesjetpack.presentation.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.charlye934.moviesjetpack.MainActivity
import com.charlye934.moviesjetpack.R
import com.charlye934.moviesjetpack.databinding.FragmentHomeBinding
import com.charlye934.moviesjetpack.databinding.ListItemBinding
import com.charlye934.moviesjetpack.presentation.HomeListener

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeListener: HomeListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setOnClickListener()
    }

    private fun setOnClickListener(){
        binding.movieButton.setOnClickListener {
            homeListener.goToMovieFragment()
        }

        binding.artistsButton.setOnClickListener {
            homeListener.goToArtistFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        homeListener = activity as MainActivity
    }

    companion object{
        val TAG = HomeFragment::class.java.simpleName
    }
}