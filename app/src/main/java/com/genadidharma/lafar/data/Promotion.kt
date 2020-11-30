package com.genadidharma.lafar.data

import androidx.annotation.DrawableRes

data class Promotion(
        val id: Long,
        val label: String,
        val title: String,
        val subtitle: String,
        @DrawableRes val image: Int
)