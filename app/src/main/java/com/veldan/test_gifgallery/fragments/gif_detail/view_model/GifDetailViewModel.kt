package com.veldan.test_gifgallery.fragments.gif_detail.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veldan.test_gifgallery.databse.GifDao
import com.veldan.test_gifgallery.databse.GifModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class GifDetailViewModel @Inject constructor(
    private val gifDao: GifDao,
) : ViewModel() {

    private val _gifs = MutableLiveData<List<GifModel>>()
    val gifs: LiveData<List<GifModel>>
        get() = _gifs

    // ----------| Database { GifDatabase } |----------

    // {fun}: getAllGifs
    fun getAllGifs() {
        viewModelScope.launch {
            _gifs.value = _getAllGifs()
        }
    }

    // {suspend fun}: getAllGifs
    private suspend fun _getAllGifs() = gifDao.getAllGifs()

}