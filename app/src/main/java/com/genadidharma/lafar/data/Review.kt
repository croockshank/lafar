package com.genadidharma.lafar.data

import java.util.*

data class Review(
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

enum class ReviewType{
    ULASAN_TERAKHIR,
    ULASAN_TEMAN
}
