package com.charlye934.moviesjetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.charlye934.moviesjetpack.data.model.artist.Artist
import com.charlye934.moviesjetpack.databinding.ActivityMainBinding
import com.charlye934.moviesjetpack.presentation.fragment.ArtistFragment
import com.charlye934.moviesjetpack.presentation.fragment.MovieFragment
import com.charlye934.moviesjetpack.presentation.fragment.TvShowFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setOnclick()
    }

    private fun setOnclick(){
        binding.movieButton.setOnClickListener {
            changeFragment(MovieFragment(), MovieFragment.TAG)
        }

        binding.artistsButton.setOnClickListener {
            changeFragment(ArtistFragment(), ArtistFragment.TAG)
        }

        binding.tvButton.setOnClickListener {
            changeFragment(TvShowFragment(), TvShowFragment.TAG)
        }
    }

    private fun changeFragment(fragment: Fragment, tag: String){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentId, fragment)
            .addToBackStack(tag)
            .commit()
    }
}