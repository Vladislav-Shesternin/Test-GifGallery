package com.veldan.test_gifgallery.interactors

import com.veldan.test_gifgallery.data.repositories.DatabaseGifRepository
import com.veldan.test_gifgallery.domain.Gif
import javax.inject.Inject

class AddGifDatabase @Inject constructor(
    private val databaseGifRepository: DatabaseGifRepository
) {

    suspend operator fun invoke(gif: Gif) {
        databaseGifRepository.add(gif)
    }

}