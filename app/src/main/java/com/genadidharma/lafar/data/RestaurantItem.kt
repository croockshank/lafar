package com.genadidharma.lafar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.genadidharma.lafar.R

object RestaurantItem {
    private val restaurants = listOf(
            Restaurant(
                    RestaurantSection.LAGI_NGETREND_BANGET,
                    1,
                    CuisineItem.getCuisine(CuisineType.LOKAL),
                    listOf(
                            R.drawable.img_restaurant_1,
                            R.drawable.img_restaurant_6
                    ),
                    5.0,
                    listOf(
                            TypeItem.getType(TypeEnum.BEBEK),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Bebek Betutu Pak Sanur",
                    "Jl. Raya Kedewatan No. 18, Ubud",
                    listOf(
                            PersonItem.getPerson(3),
                            PersonItem.getPerson(4),
                            PersonItem.getPerson(5)
                    ),
                    5.0,
                    listOf(
                            RestaurantFeature(RestaurantFeatureType.MAKAN_DI_TEMPAT, true),
                            RestaurantFeature(RestaurantFeatureType.BUNGKUS, true)
                    )
            ),
            Restaurant(
                    RestaurantSection.LAGI_NGETREND_BANGET,
                    2,
                    CuisineItem.getCuisine(CuisineType.LOKAL),
                    listOf(
                            R.drawable.img_restaurant_3,
                            R.drawable.img_restaurant_2
                    ),
                    4.3,
                    listOf(
                            TypeItem.getType(TypeEnum.BEBEK),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Bebek Bengil, Dirty Duck Diner",
                    "Jl. Raya Kedewatan No. 18, Ubud",
                    listOf(
                            PersonItem.getPerson(1),
                            PersonItem.getPerson(3),
                            PersonItem.getPerson(4)
                    ),
                    4.3,
                    listOf(
                            RestaurantFeature(RestaurantFeatureType.MAKAN_DI_TEMPAT, true),
                            RestaurantFeature(RestaurantFeatureType.BUNGKUS, true)
                    )
            ),
            Restaurant(
                    RestaurantSection.LAGI_NGETREND_BANGET,
                    3,
                    CuisineItem.getCuisine(CuisineType.LOKAL),
                    listOf(
                            R.drawable.img_restaurant_4,
                            R.drawable.img_restaurant_5
                    ),
                    4.3,
                    listOf(
                            TypeItem.getType(TypeEnum.NASIAYAM),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Nasi Ayam Kedewatan Bu Mangku",
                    "Jl. Raya Kedewatan No. 18, Ubud",
                    listOf(
                            PersonItem.getPerson(1),
                            PersonItem.getPerson(2),
                            PersonItem.getPerson(5)
                    ),
                    4.3,
                    listOf(
                            RestaurantFeature(RestaurantFeatureType.MAKAN_DI_TEMPAT, true),
                            RestaurantFeature(RestaurantFeatureType.BUNGKUS, true)
                    )
            ),
            Restaurant(
                    RestaurantSection.TEMPATNYA_INSTAGRAMMABLE,
                    3,
                    CuisineItem.getCuisine(CuisineType.FASTFOOD),
                    listOf(
                            R.drawable.img_restaurant_7,
                            R.drawable.img_restaurant_8,
                            R.drawable.img_restaurant_9
                    ),
                    4.3,
                    listOf(
                            TypeItem.getType(TypeEnum.NASIAYAM),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Milk and Madu Cafe",
                    "Jl. Raya Kedewatan No. 18, Ubud",
                    listOf(
                            PersonItem.getPerson(1),
                            PersonItem.getPerson(2),
                            PersonItem.getPerson(3)
                    ),
                    4.3,
                    listOf(
                            RestaurantFeature(RestaurantFeatureType.MAKAN_DI_TEMPAT, true),
                            RestaurantFeature(RestaurantFeatureType.BUNGKUS, true)
                    )
            ),
            Restaurant(
                    RestaurantSection.TEMPATNYA_INSTAGRAMMABLE,
                    3,
                    CuisineItem.getCuisine(CuisineType.LOKAL),
                    listOf(
                            R.drawable.img_restaurant_10,
                            R.drawable.img_restaurant_11,
                            R.drawable.img_restaurant_12
                    ),
                    4.3,
                    listOf(
                            TypeItem.getType(TypeEnum.NASIAYAM),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Starbucks Coffee",
                    "Jl. Raya Kedewatan No. 18, Ubud",
                    listOf(
                            PersonItem.getPerson(1),
                            PersonItem.getPerson(3),
                            PersonItem.getPerson(4)
                    ),
                    4.3,
                    listOf(
                            RestaurantFeature(RestaurantFeatureType.MAKAN_DI_TEMPAT, true),
                            RestaurantFeature(RestaurantFeatureType.BUNGKUS, true)
                    )
            ),
            Restaurant(
                    RestaurantSection.TEMPATNYA_INSTAGRAMMABLE,
                    3,
                    CuisineItem.getCuisine(CuisineType.LOKAL),
                    listOf(
                            R.drawable.img_restaurant_13,
                            R.drawable.img_restaurant_14,
                            R.drawable.img_restaurant_15
                    ),
                    4.3,
                    listOf(
                            TypeItem.getType(TypeEnum.NASIAYAM),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Folk Pool & Gardens",
                    "Jl. Raya Kedewatan No. 18, Ubud",
                    listOf(
                            PersonItem.getPerson(2),
                            PersonItem.getPerson(3),
                            PersonItem.getPerson(5)
                    ),
                    4.3,
                    listOf(
                            RestaurantFeature(RestaurantFeatureType.MAKAN_DI_TEMPAT, true),
                            RestaurantFeature(RestaurantFeatureType.BUNGKUS, true)
                    )
            )
    )

    private val _restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()

    fun getRestaurants(section: RestaurantSection, key: Cuisine?): LiveData<List<Restaurant>> {
        return when (key?.key) {
            CuisineType.SEMUA -> {
                Transformations.map(_restaurants) { restaurant ->
                    restaurant.filter { it.section == section }.toList()
                }
            }
            else -> {
                Transformations.map(_restaurants) { restaurant ->
                    restaurant.filter { it.section == section && it.cuisine == key }.toList()
                }
            }
        }
    }

    fun getRestaurant(id: Int): Restaurant? {
        return restaurants.find {
            it.id == id
        }
    }

    init {
        _restaurants.value = restaurants
    }
}