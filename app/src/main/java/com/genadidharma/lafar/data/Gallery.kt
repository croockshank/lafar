package com.genadidharma.lafar.data

import androidx.annotation.DrawableRes

data class Gallery(
        val type: GalleryType,
        val restaurant: Restaurant?,
        val assets: List<Asset>
)

data class Asset(
        val id: Int,
        val type: AssetType,
        @DrawableRes val asset: Int,
        val person: Person?
)

enum class GalleryType {
    ALBUM,
    POSTINGAN_TEMAN
}

enum class AssetType{
    IMAGE,
    VIDEO
}