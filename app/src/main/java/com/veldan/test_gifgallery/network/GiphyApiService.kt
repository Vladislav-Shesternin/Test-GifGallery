package com.veldan.test_gifgallery.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val GIPHY_URL = "https://api.giphy.com/v1/gifs/"

private const val api_key = "febS2xeBZ3OAN251ILkTxkeSNgLWGvGG"
private const val limit = 25
private const val rating = "g"
private const val TRENDING_URL = "trending?api_key=$api_key&limit=$limit&rating=$rating"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(GIPHY_URL)
    .build()

interface GiphyApiService {
    @GET(TRENDING_URL)
    suspend fun getTrendingGifs(): Gifs
}

object GiphyApi {
    val retrofitService: GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }
}