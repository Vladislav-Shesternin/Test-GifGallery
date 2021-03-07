package com.veldan.test_gifgallery.network

data class Gifs(
    val data: List<GifProperty>
)

data class GifProperty(
    val id: String
)