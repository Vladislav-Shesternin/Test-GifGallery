package com.veldan.test_gifgallery.data.repositories

import com.veldan.test_gifgallery.data.dataSources.NetworkGifDataSource
import com.veldan.test_gifgallery.domain.Gif
import com.veldan.test_gifgallery.framework.network.GiphyApiService
import retrofit2.Retrofit
import javax.inject.Inject

class NetworkGifRepository @Inject constructor(
    private val retrofit: Retrofit
) : NetworkGifDataSource {

    override suspend fun readAll(): List<Gif> {
        return retrofit.create(GiphyApiService::class.java)
            .getTrendingGifs().data.map {
                Gif(
                    id = it.id,
                    urlFixedWidth = it.images.fixedWidth.url,
                    urlFixedHeight = it.images.fixedHeight.url
                )
            }
    }
}