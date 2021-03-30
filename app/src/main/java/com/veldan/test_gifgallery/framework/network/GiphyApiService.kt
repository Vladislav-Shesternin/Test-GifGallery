package com.veldan.test_gifgallery.framework.network

import retrofit2.http.GET

const val GIPHY_URL = "https://api.giphy.com/v1/gifs/"

private const val api_key = "febS2xeBZ3OAN251ILkTxkeSNgLWGvGG"
private const val limit = 50
private const val rating = "g"
private const val TRENDING_URL = "trending?api_key=$api_key&limit=$limit&rating=$rating"

interface GiphyApiService {
    @GET(TRENDING_URL)
    suspend fun getTrendingGifs(): Gifs
}