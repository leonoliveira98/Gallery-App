package com.example.galleryapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("?method=flickr.photos.search&api_key=bf7a6e072924fa21ff740f4e8c151f4f&format=json&nojsoncallback=1")
    fun getPhotoList(@Query ("tags") tags: String, @Query ("page") page: Int) : Call<SearchPhotosResponse>
}