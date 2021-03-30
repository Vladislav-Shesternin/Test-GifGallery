package com.veldan.test_gifgallery.data.repositories

import com.veldan.test_gifgallery.data.dataSources.DatabaseGifDataSource
import com.veldan.test_gifgallery.domain.Gif
import com.veldan.test_gifgallery.framework.databse.GifDao
import com.veldan.test_gifgallery.framework.databse.GifModel
import javax.inject.Inject

class DatabaseGifRepository @Inject constructor(
    private val gifDao: GifDao
) : DatabaseGifDataSource {

    override suspend fun add(gif: Gif) {
        gifDao.insertGif(
            GifModel(
                id = gif.id,
                urlFixedWidth = gif.urlFixedWidth,
                urlFixedHeight = gif.urlFixedHeight
            )
        )
    }

    override suspend fun readAll(): List<Gif> {
        return gifDao.getAllGifs()
            .map {
                Gif(
                    id = it.id,
                    urlFixedWidth = it.urlFixedWidth,
                    urlFixedHeight = it.urlFixedHeight
                )
            }
    }

    override suspend fun clear() {
        gifDao.clear()
    }

}