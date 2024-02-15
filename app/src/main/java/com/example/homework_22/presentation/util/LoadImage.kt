package com.example.homework_22.presentation.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun ImageView.loadImage(url: String, cornerRadius: Int = 0) {
    val glideRequest = Glide.with(this.context)
        .load(url)
    if (cornerRadius > 0) {
        glideRequest.transform(CenterCrop(), RoundedCorners(cornerRadius))
    } else {
        glideRequest.centerCrop()
    }
    glideRequest.into(this)
}