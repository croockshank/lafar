package com.genadidharma.lafar.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Restaurant(
        val section: @RawValue RestaurantSection,
        val id: @RawValue Int,
        val cuisine: @RawValue Cuisine?,
        @DrawableRes val images: List<Int>,
        val rating: @RawValue Double,
        val types: @RawValue List<Type?>,
        val title: @RawValue String,
        val address: @RawValue String,
        val friends: @RawValue List<Person?>,
        val distance: @RawValue Double,
        val features: @RawValue List<RestaurantFeature>
) : Parcelable

enum class RestaurantSection {
    LAGI_NGETREND_BANGET,
    TEMPATNYA_INSTAGRAMMABLE
}

object RestaurantDiffCallback : DiffUtil.ItemCallback<Restaurant>() {
    override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean = oldItem.id == newItem.id
}
