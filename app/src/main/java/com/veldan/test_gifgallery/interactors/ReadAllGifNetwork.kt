package com.veldan.test_gifgallery.interactors

import com.veldan.test_gifgallery.data.repositories.NetworkGifRepository
import com.veldan.test_gifgallery.domain.Gif
import javax.inject.Inject

class ReadAllGifNetwork @Inject constructor(
    private val networkGifRepository: NetworkGifRepository
) {

    suspend operator fun invoke(): List<Gif> {
        return networkGifRepository.readAll()
    }

}