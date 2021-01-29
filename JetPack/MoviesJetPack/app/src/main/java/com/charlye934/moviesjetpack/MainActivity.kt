package com.charlye934.moviesjetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.charlye934.moviesjetpack.databinding.ActivityMainBinding
import com.charlye934.moviesjetpack.presentation.HomeListener
import com.charlye934.moviesjetpack.presentation.fragment.HomeFragment
import com.charlye934.moviesjetpack.presentation.fragment.artist.ArtistFragment
import com.charlye934.moviesjetpack.presentation.fragment.movie.MovieFragment

class MainActivity : AppCompatActivity(), HomeListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        changeFragment(HomeFragment(), HomeFragment.TAG)
    }

    override fun goToMovieFragment() {
        changeFragment(MovieFragment(), MovieFragment.TAG)
    }

    override fun goToArtistFragment() {
        changeFragment(ArtistFragment(), ArtistFragment.TAG)
    }

    private fun changeFragment(fragment: Fragment, tag: String){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentId, fragment)
            .addToBackStack(tag)
            .commit()
    }
}