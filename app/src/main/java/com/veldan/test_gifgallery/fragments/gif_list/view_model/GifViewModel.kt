package com.veldan.test_gifgallery.fragments.gif_list.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veldan.test_gifgallery.databse.GifDao
import com.veldan.test_gifgallery.databse.GifModel
import com.veldan.test_gifgallery.network.GifProperty
import com.veldan.test_gifgallery.network.GiphyApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class GifViewModel @Inject constructor(
    private val gifDao: GifDao,
    private val retrofit: Retrofit
) : ViewModel() {

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

    // ------------------------------------------------------------| Network { GiphyApiService } |
    init {
        getGifs()
    }

    // {fun}: getGifs
    private fun getGifs() {
        viewModelScope.launch {
            try {
                val gifs = retrofit.create(GiphyApiService::class.java).getTrendingGifs().data
                _response.value = gifs
            } catch (e: Exception) {
                _response.value = null
                Log.i(TAG, "Failure: ${e.message}")
            }
        }
    }

    // ------------------------------------------------------------| Database { GifDatabase } |

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
