package com.veldan.test_gifgallery.gif_list.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veldan.test_gifgallery.network.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GifViewModel : ViewModel() {
    private val TAG = this::class.simpleName

    private val _response = MutableLiveData<List<GifProperty>?>()
    val response: LiveData<List<GifProperty>?>
        get() = _response

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
}
