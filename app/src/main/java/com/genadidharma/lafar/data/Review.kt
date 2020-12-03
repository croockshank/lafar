package com.genadidharma.lafar.data

import androidx.recyclerview.widget.DiffUtil

data class Review(
        val id: Int,
        val restaurant: Restaurant?,
        val type: ReviewType,
        val person: Person?,
        val rating: Double,
        val date: String,
        val isIndonesian: Boolean,
        val review: String,
        val favoriteCount: Int,
        val commentCount: Int
)

enum class ReviewType {
    ULASAN_TERAKHIR,
    ULASAN_TEMAN
}

object ReviewDiffCallback : DiffUtil.ItemCallback<Review>() {
    override fun areItemsTheSame(oldItem: Review, newItem: Review): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Review, newItem: Review): Boolean = oldItem.id == newItem.id

}
