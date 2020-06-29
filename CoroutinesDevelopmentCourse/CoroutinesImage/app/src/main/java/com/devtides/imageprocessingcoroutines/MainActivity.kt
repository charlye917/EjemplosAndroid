package com.devtides.imageprocessingcoroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val IMAGE_URL = "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope.launch {
            val originalDeferred = coroutineScope.async(Dispatchers.IO){ getOriginalBitmap() }
            val originalBitmap = originalDeferred.await()

            val filterDeferred = coroutineScope.async(Dispatchers.Default){ applyfilster((originalBitmap)) }
            val filteredBitma = filterDeferred.await()

            loadImage(filteredBitma)
        }
    }

    private fun getOriginalBitmap() =
        URL(IMAGE_URL).openStream().use {
            BitmapFactory.decodeStream(it)
        }

    private fun applyfilster(originalBitmap: Bitmap) = Filter.apply(originalBitmap)

    private fun loadImage(btm:Bitmap){
        progressBar.visibility = View.GONE
        imageView.setImageBitmap(btm)
        imageView.visibility = View.VISIBLE
    }

}
