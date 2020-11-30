package com.genadidharma.lafar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.genadidharma.lafar.R

object PromotionItem {
    private val allPromotions = listOf(
            Promotion(
                    1,
                    "Mulai dari",
                    "7 ribuan",
                    "Yang seger-seger supaya nggak gerah lagi",
                    R.drawable.carousel_image_1
            ),
            Promotion(
                    2,
                    "Makan sampai",
                    "Puas",
                    "Ada promo nunggu nih buat kamu",
                    R.drawable.carousel_image_1
            ),
            Promotion(
                    3,
                    "Selalu pakai",
                    "Masker",
                    "Supaya jalan-jalannya tetep happy",
                    R.drawable.carousel_image_1
            )
    )

    private val _promotions: MutableLiveData<List<Promotion>> = MutableLiveData()

    fun getPromotions(): LiveData<List<Promotion>> {
        return _promotions
    }

    init {
        _promotions.value = allPromotions
    }
}