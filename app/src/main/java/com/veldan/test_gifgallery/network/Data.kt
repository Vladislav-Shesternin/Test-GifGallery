package com.veldan.test_gifgallery.network

import com.squareup.moshi.Json


/**
 *Example JSON is in: [Scratches and Console] -> [Scratches] -> [Gif-Tranding-Result]
 *
 * */
data class Gifs(
    @Json(name = "data")
    val data: List<GifProperty>
)

data class GifProperty(
    @Json(name = "id")
    val id: String,

    @Json(name = "images")
    val images: Images
)

data class Images(
    @Json(name = "fixed_width")
    val fixedWidth: FixedWidth,

    @Json(name = "fixed_height")
    val fixedHeight: FixedHeight
)

data class FixedWidth(
    @Json(name = "url")
    val url: String
)

data class FixedHeight(
    @Json(name = "url")
    val url: String
)