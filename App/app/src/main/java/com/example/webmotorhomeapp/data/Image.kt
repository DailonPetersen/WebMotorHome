package com.example.webmotorhomeapp.data

import android.graphics.drawable.Drawable

data class Image(val url: String, val width: Int, val height: Int, val title: String) {
    val aspectRatio: Float
        get() = width.toFloat() / height.toFloat()
}