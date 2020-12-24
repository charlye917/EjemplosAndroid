package com.charlye934.navigationcomponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.charlye934.navigationcomponent.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var bindign: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindign = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }
}