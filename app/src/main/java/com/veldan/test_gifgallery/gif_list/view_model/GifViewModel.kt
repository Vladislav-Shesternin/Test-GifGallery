package com.veldan.test_gifgallery.gif_list.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veldan.test_gifgallery.network.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GifViewModel : ViewModel() {
    private val TAG = this::class.simpleName

    private val _response = MutableLiveData<List<GifProperty>>()
    val response: LiveData<List<GifProperty>>
        get() = _response

    init {
        getGifs()
    }

    private fun getGifs() {
        GiphyApi.retrofitService.getTrendingGifs().enqueue(
            object : Callback<Gifs> {

                override fun onResponse(call: Call<Gifs>, response: Response<Gifs>) {
                    response.body()?.let {
                        _response.value = it.data
                    }
                }

                override fun onFailure(call: Call<Gifs>, t: Throwable) {
                    Log.i(TAG, "onFailure: ${t.message}")
                }
            }
        )
    }
}
