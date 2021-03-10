package com.veldan.test_gifgallery.fragments.gif_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.veldan.test_gifgallery.databse.GifDao

class GifViewModelFactory(
    private val gifDao: GifDao,
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GifViewModel::class.java)) {
            return GifViewModel(gifDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}