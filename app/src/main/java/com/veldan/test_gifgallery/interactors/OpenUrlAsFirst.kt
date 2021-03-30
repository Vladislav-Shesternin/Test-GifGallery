package com.veldan.test_gifgallery.interactors

import com.veldan.test_gifgallery.domain.Gif
import javax.inject.Inject

class OpenUrlAsFirst @Inject constructor() {

    operator fun invoke(firstUrl: String, gifList: List<Gif>): List<String> {
        // List: urls FixedHeight
        return listOf(firstUrl) +
                gifList.map { gif ->
                    gif.urlFixedHeight
                }.filterNot { url ->
                    url == firstUrl
                }
    }

}