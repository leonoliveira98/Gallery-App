package com.example.galleryapp

import java.io.Serializable

class Images {

    private val id : String? = null
    private val sizeSquare : String? = null
    private val sizeLargeSquare : String? = null

    fun getInfo():String{
        return "id: $id, Square Size: $sizeSquare, Large Size: $sizeLargeSquare"
    }


}