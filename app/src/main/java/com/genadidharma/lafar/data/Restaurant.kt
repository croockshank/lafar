package com.genadidharma.lafar.data

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil

data class Restaurant(
        val section: RestaurantSection,
        val id: Int,
        val cuisine: Cuisine?,
        @DrawableRes val image: Int,
        val rating: Double,
        val types: List<Type?>,
        val title: String,
        val friends: List<Person?>,
        val distance: Double
)

enum class RestaurantSection{
    LAGI_NGETREND_BANGET,
    TEMPATNYA_INSTAGRAMMABLE
}

object RestaurantDiffCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean = oldItem.id == newItem.id
}
