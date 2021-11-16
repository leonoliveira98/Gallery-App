package com.example.galleryapp

class ApiConfig {

    // The base url to connect to the server
    val BASE_URL = "https://api.flickr.com/services/rest/"

    object EndPoint {

        const val IMAGENS = "?method=flickr.photos.search&api_key=bf7a6e072924fa21ff740f4e8c151f4f&tags=Paisagens&page=1&format=json&nojsoncallback=1"
    }
}