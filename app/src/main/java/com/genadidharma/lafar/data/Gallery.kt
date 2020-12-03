package com.genadidharma.lafar.data

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil

data class Gallery(
        val restaurant: Restaurant?,
        val assets: List<Asset>
)

data class Asset(
        val galleryType: GalleryType,
        val id: Int,
        val assetType: AssetType,
        @DrawableRes val asset: Int,
        val person: Person?,
        val date: String?
)

enum class GalleryType {
    ALBUM,
    POSTINGAN_TEMAN
}

enum class AssetType{
    IMAGE,
    VIDEO
}

object AssetDiffCallback : DiffUtil.ItemCallback<Asset>() {
    override fun areItemsTheSame(oldItem: Asset, newItem: Asset): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Asset, newItem: Asset): Boolean = oldItem.id == newItem.id
}