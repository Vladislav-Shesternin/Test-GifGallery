package com.veldan.test_gifgallery.fragments.gif_list.view_model

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.veldan.test_gifgallery.databse.GifDao
import com.veldan.test_gifgallery.databse.GifDatabase
import com.veldan.test_gifgallery.databse.GifModel
import com.veldan.test_gifgallery.network.GifProperty
import com.veldan.test_gifgallery.network.GiphyApi
import kotlinx.coroutines.launch

class GifViewModel(
    private val gifDao: GifDao,
    application: Application
) : AndroidViewModel(application) {

    private val TAG = this::class.simpleName

    // Response from Network
    private val _response = MutableLiveData<List<GifProperty>?>()
    val response: LiveData<List<GifProperty>?>
        get() = _response

    // Navigation to GifDetail
    private val _navigateToGifDetail = MutableLiveData<String?>()
    val navigateToGifDetail: LiveData<String?>
        get() = _navigateToGifDetail

    // {fun}: onGifItemClicked
    fun onGifItemClicked(gifUrl: String) {
        _navigateToGifDetail.value = gifUrl
    }

    // {fun}: onGifDetailNavigated
    fun onGifDetailNavigated() {
        _navigateToGifDetail.value = null
    }

    // ----------| Network { GiphyApiService } |----------
    init {
        getGifs()
    }

    // {fun}: getGifs
    private fun getGifs() {
        viewModelScope.launch {
            try {
                val gifs = GiphyApi.retrofitService.getTrendingGifs().data
                _response.value = gifs
            } catch (e: Exception) {
                _response.value = null
                Log.i(TAG, "Failure: ${e.message}")
            }
        }
    }

    // ----------| Database { GifDatabase } |----------

    // {fun}: insertGif
    fun insertGif(gif: GifModel) {
        viewModelScope.launch {
            _insertGif(gif)
        }
    }

    //{suspend fun}: insertGif
    private suspend fun _insertGif(gif: GifModel) {
        gifDao.insertGif(gif)
    }
}
