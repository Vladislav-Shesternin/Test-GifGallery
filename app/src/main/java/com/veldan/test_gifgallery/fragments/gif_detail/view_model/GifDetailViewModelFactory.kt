package com.veldan.test_gifgallery.fragments.gif_detail.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.veldan.test_gifgallery.databse.GifDao
import java.lang.IllegalArgumentException

//class GifDetailViewModelFactory(
//    private val gifDao: GifDao
//): ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(GifDetailViewModel::class.java)){
//            return GifDetailViewModel(gifDao) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}