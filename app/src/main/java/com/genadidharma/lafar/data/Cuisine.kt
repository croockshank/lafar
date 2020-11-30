package com.genadidharma.lafar.data

import androidx.annotation.DrawableRes

data class Cuisine(
        val key: CuisineType,
        val name: String,
        @DrawableRes val image: Int
)

enum class CuisineType(val id: Int, val type: String){
    SEMUA(1, "Semua"),
    LOKAL(2,"Lokal"),
    SEAFOOD(3,"Seafood"),
    FASTFOOD(4,"Fast Food"),
    VEGETARIAN(5,"Vegetarian")
}