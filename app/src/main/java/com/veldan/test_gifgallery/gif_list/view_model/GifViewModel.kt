package com.veldan.test_gifgallery.gif_list.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.veldan.test_gifgallery.network.GifProperty
import com.veldan.test_gifgallery.network.Gifs
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
            object : Callback<Gifs> {

                override fun onResponse(
                    call: Call<Gifs>,
                    response: Response<Gifs>
                ) {
                    Log.i("GifListFragment", "onResponse: ${response.body()}")
                }

                override fun onFailure(call: Call<Gifs>, t: Throwable) {
                    _response.value = "Failure: " + t.message
                }

            }
        )
    }
}
