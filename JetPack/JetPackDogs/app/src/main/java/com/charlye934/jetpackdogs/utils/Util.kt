package com.charlye934.jetpackdogs.utils

import android.content.Context
import android.transition.CircularPropagation
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.charlye934.jetpackdogs.R

val PERMISSION_SEND_SMS = 234

fun getProgresDrawable(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 10f
        start()
    }
}

fun ImageView.loadImages(url:String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

@BindingAdapter("android:imagesUrl")
fun loadImages(view: ImageView, url: String? = ""){
    view.loadImages(url, getProgresDrawable(view.context))
}