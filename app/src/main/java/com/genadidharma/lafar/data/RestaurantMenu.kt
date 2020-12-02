package com.genadidharma.lafar.data

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.DiffUtil
import com.genadidharma.lafar.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class RestaurantMenu(
        val restaurant: Restaurant?,
        val menu: List<Menu>
)

@Parcelize
data class Menu(
        val id: @RawValue Int,
        val type: @RawValue MenuType,
        val name: @RawValue String,
        @DrawableRes val images: @RawValue List<Int>,
        val price: @RawValue Long,
        val desc: @RawValue String,
        val addOns: @RawValue List<AddOn>
) : Parcelable

data class AddOn(
        val id: Int,
        val name: String,
        @DrawableRes val image: Int,
        val price: Long
)

enum class MenuType(val titleRes: Int) {
    BEST_SELLER(R.string.best_seller_menu),
    PROMO_WEEKEND(R.string.promo_weekend_menu),
    MAKANAN(R.string.makanan_menu),
    MINUMAN(R.string.minuman_menu),
}

object MenuDiffCallback : DiffUtil.ItemCallback<Menu>() {
    override fun areItemsTheSame(oldItem: Menu, newItem: Menu): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Menu, newItem: Menu): Boolean = oldItem.id == newItem.id
}

object AddonDiffCallback : DiffUtil.ItemCallback<AddOn>() {
    override fun areItemsTheSame(oldItem: AddOn, newItem: AddOn): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: AddOn, newItem: AddOn): Boolean = oldItem.id == newItem.id
}