package com.veldan.test_gifgallery.fragments.gif_list.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veldan.test_gifgallery.network.GifProperty
import com.veldan.test_gifgallery.network.GiphyApi
import kotlinx.coroutines.launch

class GifViewModel : ViewModel() {
    private val TAG = this::class.simpleName

    private val _response = MutableLiveData<List<GifProperty>?>()
    val response: LiveData<List<GifProperty>?>
        get() = _response

    private val _navigateToGifDetail = MutableLiveData<String?>()
    val navigateToGifDetail: LiveData<String?>
        get() = _navigateToGifDetail

    init {
        getGifs()
    }

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

    fun onGifItemClicked(gifUrl: String) {
        _navigateToGifDetail.value = gifUrl
    }

    fun onGifDetailNavigated() {
        _navigateToGifDetail.value = null
    }
}
