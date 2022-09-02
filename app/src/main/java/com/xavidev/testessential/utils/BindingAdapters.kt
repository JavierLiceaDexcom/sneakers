package com.xavidev.testessential.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.xavidev.testessential.R

@BindingAdapter("imageUrl")
fun loadImageUrl(view: ImageView, url: String?) {
    url?.let {
        Picasso.get().load(it).placeholder(R.drawable.ic_loading_placeholder)
            .error(R.drawable.ic_no_image)
            .into(view)
    }
}