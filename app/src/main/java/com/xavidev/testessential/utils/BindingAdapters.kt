package com.xavidev.testessential.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun loadImageUrl(view: ImageView, url: String?) {
    url?.let {
        Picasso.get().isLoggingEnabled = true
        Picasso.get().load(it).into(view)
    }
}