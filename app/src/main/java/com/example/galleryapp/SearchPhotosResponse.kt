package com.example.galleryapp

import com.google.gson.annotations.SerializedName

data class SearchPhotosResponse(

    @SerializedName("photos")
    val photosListInfo: PhotosInfo,
    val stat: String

) {
    data class PhotosInfo (
        val page: Int,
        val pages: Int,
        val perpage: Int,
        val photo: List<Photo>,
        val total: Int
    ) {

        data class Photo (
            var id: String,
            val owner : String
        )
    }
}