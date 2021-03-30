package com.veldan.test_gifgallery.presentation.fragments.gifList.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.veldan.test_gifgallery.domain.Gif
import com.veldan.test_gifgallery.framework.network.GiphyApiService
import com.veldan.test_gifgallery.interactors.AddGifDatabase
import com.veldan.test_gifgallery.interactors.ReadAllGifDatabase
import com.veldan.test_gifgallery.interactors.ReadAllGifNetwork
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject

class GifViewModel @Inject constructor(
    // Database
    private val addGifDatabase: AddGifDatabase,
    private val readAllGifDatabase: ReadAllGifDatabase,
    // Network
    private val readAllGifNetwork: ReadAllGifNetwork
) : ViewModel() {

    private val TAG = this::class.simpleName

    // Response from Network
    private val _response = MutableLiveData<List<Gif>>()
    val response: LiveData<List<Gif>>
        get() = _response

    // Navigation to GifDetail
    private val _navigateToGifDetail = MutableLiveData<String?>()
    val navigateToGifDetail: LiveData<String?>
        get() = _navigateToGifDetail

    // ------------------------------------------------------------| Initializer |
    init {
        getGifs()
    }

    // ------------------------------------------------------------| Navigation to GifDetail |
    // {fun}: onGifItemClicked
    fun onGifItemClicked(gifUrl: String) {
        _navigateToGifDetail.value = gifUrl
    }

    // {fun}: onGifDetailNavigated
    fun onGifDetailNavigated() {
        _navigateToGifDetail.value = null
    }

    // ------------------------------------------------------------| Network { GiphyApiService } |
    // {fun}: getGifs
    private fun getGifs() {
        viewModelScope.launch {
            try {
                readAllGifNetwork().also { listGif ->
                    _response.value = listGif
                    listGif.forEach { insertGif(it) }
                }
            } catch (e: Exception) {
                _response.value = readAllGifDatabase()
                Log.i(TAG, "Failure: ${e.message}")
            }
        }
    }

    // ------------------------------------------------------------| Database { GifDatabase } |
    // {fun}: insertGif
    private fun insertGif(gif: Gif) {
        viewModelScope.launch {
            addGifDatabase(gif)
        }
    }
}
