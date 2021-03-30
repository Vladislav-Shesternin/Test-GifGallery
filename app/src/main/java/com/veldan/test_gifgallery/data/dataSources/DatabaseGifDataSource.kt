package com.veldan.test_gifgallery.data.dataSources

import com.veldan.test_gifgallery.domain.Gif

interface DatabaseGifDataSource {

    suspend fun add(gif: Gif)

    suspend fun readAll(): List<Gif>

    suspend fun clear()
}