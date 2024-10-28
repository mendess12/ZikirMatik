package com.yusufmendes.zikirmatik.util.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.downloadImage(image_url:String){
    Glide.with(this)
        .load(image_url)
        .into(this)
}