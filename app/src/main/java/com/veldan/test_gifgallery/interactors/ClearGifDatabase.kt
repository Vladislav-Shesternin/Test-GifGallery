package com.veldan.test_gifgallery.interactors

import com.veldan.test_gifgallery.data.repositories.DatabaseGifRepository
import javax.inject.Inject

class ClearGifDatabase @Inject constructor(
    private val databaseGifRepository: DatabaseGifRepository
) {

    suspend operator fun invoke() {
        databaseGifRepository.clear()
    }

}