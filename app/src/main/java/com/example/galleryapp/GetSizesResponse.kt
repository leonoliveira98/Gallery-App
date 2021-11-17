package com.example.galleryapp

data class GetSizesResponse(
    val sizes: Sizes,
    val stat: String

) {
    data class Sizes(
        val canblog: Int,
        val candownload: Int,
        val canprint: Int,
        val size: List<Size>

    ) {
        data class Size(
            val url: String,
            val height: Int,
            val width: Int

        )
    }
}