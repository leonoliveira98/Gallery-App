package com.example.galleryapp

data class PhotoInformation (
    var info: List<SourcePhoto>,

) {
    data class SourcePhoto(
        var labelSquare : String,
        var sourceSquare : String,
        var heightSquare : String,
        var widthSquare : String,

        var labelLarge : String,
        var sourceLarge : String,
        var heightLarge : String,
        var widthLarge : String
    )
}

