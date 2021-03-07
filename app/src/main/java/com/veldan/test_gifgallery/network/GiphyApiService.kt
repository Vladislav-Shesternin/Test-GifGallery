package com.veldan.test_gifgallery.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val GIPHY_URL = "https://api.giphy.com/v1/gifs/"

private const val api_key = "febS2xeBZ3OAN251ILkTxkeSNgLWGvGG"
private const val limit = 25
private const val rating = "g"
private const val TRENDING_URL = "trending?api_key=$api_key&limit=$limit&rating=$rating"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(GIPHY_URL)
    .build()

interface GiphyApiService {
    @GET(TRENDING_URL)
    fun getTrendingGifs(): Call<String>
}

object GiphyApi {
    val retrofitService: GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }
}