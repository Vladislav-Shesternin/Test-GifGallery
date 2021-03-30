package com.veldan.test_gifgallery.presentation.fragments.gifDetail.view_model

import androidx.lifecycle.*
import com.veldan.test_gifgallery.interactors.OpenUrlAsFirst
import com.veldan.test_gifgallery.interactors.ReadAllGifDatabase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.launch

class GifDetailViewModel @AssistedInject constructor(
    @Assisted private val gifUrl: String,
    // Database
    private val readAllGifDatabase: ReadAllGifDatabase,
    // Simple
    private val openUrlAsFirst: OpenUrlAsFirst
) : ViewModel() {

    private val _gifs = MutableLiveData<List<String>>()
    val gifs: LiveData<List<String>>
        get() = _gifs

    // ------------------------------------------------------------| Initializer |
    init {
        getAllGifs()
    }

    // ------------------------------------------------------------| Database { GifDatabase } |
    // {fun}: getAllGifs
    private fun getAllGifs() {
        viewModelScope.launch {
            _gifs.value = openUrlAsFirst(gifUrl, readAllGifDatabase())
        }
    }

    @dagger.assisted.AssistedFactory
    interface AssistedFactory {
        fun create(gifUrl: String): GifDetailViewModel
    }

    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            gifUrl: String
        ): ViewModelProvider.Factory {
            return object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                    return assistedFactory.create(gifUrl) as T
                }
            }
        }
    }
}
