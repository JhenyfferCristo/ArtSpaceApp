package com.example.artspaceapp

import com.example.artspaceapp.model.ArtImage
import com.example.artspaceapp.R.*

class DataSource() {
    fun loadArtImage(): List<ArtImage> {
        return listOf<ArtImage>(
            ArtImage(string.art_title1, string.art_author, drawable.img_2961),
            ArtImage(string.art_title2, string.art_author,drawable.img_8971),
            ArtImage(string.art_title3,string.art_author, drawable.img_9077)
        )
    }
}
