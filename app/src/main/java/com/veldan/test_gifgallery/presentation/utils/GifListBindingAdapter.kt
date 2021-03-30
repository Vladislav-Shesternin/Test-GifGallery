package com.veldan.test_gifgallery.presentation.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.veldan.test_gifgallery.R

@BindingAdapter("setGif")
fun ImageView.setGif(url: String) {
    Glide.with(this)
        .load(url)
        .thumbnail(
            Glide.with(this)
                .load(R.drawable.loading)
        )
        .into(this)
}