package com.veldan.test_gifgallery.fragments.gif_list.view_model

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.veldan.test_gifgallery.databse.GifDao
import java.lang.IllegalArgumentException

class GifViewModelFactory(
    private val gifDao: GifDao,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GifViewModel::class.java)) {
            return GifViewModel(gifDao, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}