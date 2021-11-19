package com.example.galleryapp

data class PhotoInformation (
    val info: List<SourcePhoto>,
//    val infoLarge: List<SourceLargePhoto>

) {
    data class SourcePhoto(
        var idSquare : String,
        var labelSquare : String,
        var sourceSquare : String,
        var heightSquare : String,
        var widthSquare : String,
        var idLarge : String,
        var labelLarge : String,
        var sourceLarge : String,
        var heightLarge : String,
        var widthLarge : String
    )
}

