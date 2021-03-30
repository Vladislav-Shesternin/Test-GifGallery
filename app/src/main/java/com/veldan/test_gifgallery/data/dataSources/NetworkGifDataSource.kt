package com.veldan.test_gifgallery.data.dataSources

import com.veldan.test_gifgallery.domain.Gif

interface NetworkGifDataSource {

    suspend fun readAll(): List<Gif>
}