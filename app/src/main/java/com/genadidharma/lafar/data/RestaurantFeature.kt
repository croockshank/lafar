package com.genadidharma.lafar.data

data class RestaurantFeature(
        val name: RestaurantFeatureType,
        val isAvailable: Boolean
)

enum class RestaurantFeatureType(val type: String){
    MAKAN_DI_TEMPAT("Makan di Tempat"),
    BUNGKUS("Bungkus")
}