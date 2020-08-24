package com.charlye934.openappmaps

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

//Intent implicito
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMaps.setOnClickListener {
            showMaps(Uri.parse("geo:47.6,-122.3?z=11"))
        }
    }

    private fun showMaps(geoLocation:Uri){
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocation
        }

        if(intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        }
    }
}