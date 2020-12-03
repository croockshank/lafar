package com.genadidharma.lafar.data

import GalleryItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations

object ReviewItem {
    val reviews = listOf(
            Review(
                    1,
                    RestaurantItem.getRestaurant(1),
                    ReviewType.ULASAN_TEMAN,
                    PersonItem.getPerson(3),
                    4.5,
                    "December 2, 2020",
                    true,
                    """
                      Harganya lumayan terjangkau, dapatnya banyak, tempatnya bersih dan pokoknya Bali banget deh!  
                    """.trimIndent(),
                    20,
                    13
            ),
            Review(1,
                    RestaurantItem.getRestaurant(1),
                    ReviewType.ULASAN_TERAKHIR,
                    PersonItem.getPerson(4),
                    4.5,
                    "December 2, 2020",
                    true,
                    """
                      Harganya lumayan terjangkau, dapatnya banyak, tempatnya bersih dan pokoknya Bali banget deh!  
                    """.trimIndent(),
                    20,
                    13
            )
    )

    private val _reviews: MutableLiveData<List<Review>> = MutableLiveData()

    fun getReviews(restaurant: Restaurant, type: ReviewType): LiveData<List<Review>>? {
        return Transformations.map(_reviews) { reviews ->
            reviews.filter {
                it.restaurant == restaurant && it.type == type
            }.toList()
        }
    }

    fun getReviews() : LiveData<List<Review>>{
        return Transformations.map(_reviews) { reviews ->
            reviews
        }
    }

    init {
        _reviews.value = reviews
    }
}