package com.veldan.test_gifgallery.gif_list.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veldan.test_gifgallery.network.GiphyApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GifViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response

    init {
        getGifs()
    }

    private fun getGifs() {
        GiphyApi.retrofitService.getTrendingGifs().enqueue(
            object : Callback<String> {

                override fun onResponse(call: Call<String>, response: Response<String>) {
                     _response.value = response.body()
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

            }
        )
    }
}
