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
                    R.drawable.img_restaurant_1,
                    5.0,
                    listOf(
                            TypeItem.getType(TypeEnum.BEBEK),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Bebek Betutu Pak Sanur",
                    listOf(
                            PersonItem.getPerson(1),
                            PersonItem.getPerson(2),
                            PersonItem.getPerson(3),
                            PersonItem.getPerson(4),
                            PersonItem.getPerson(5)
                    ),
                    5.0
            ),
            Restaurant(
                    RestaurantSection.LAGI_NGETREND_BANGET,
                    2,
                    CuisineItem.getCuisine(CuisineType.LOKAL),
                    R.drawable.img_restaurant_1,
                    4.3,
                    listOf(
                            TypeItem.getType(TypeEnum.BEBEK),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Bebek Bengil, Dirty Duck Diner",
                    listOf(
                            PersonItem.getPerson(1),
                            PersonItem.getPerson(2),
                            PersonItem.getPerson(3),
                            PersonItem.getPerson(4),
                            PersonItem.getPerson(5)
                    ),
                    4.3
            ),
            Restaurant(
                    RestaurantSection.LAGI_NGETREND_BANGET,
                    3,
                    CuisineItem.getCuisine(CuisineType.LOKAL),
                    R.drawable.img_restaurant_1,
                    4.3,
                    listOf(
                            TypeItem.getType(TypeEnum.NASIAYAM),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Nasi Ayam Kedewatan Bu Mangku",
                    listOf(
                            PersonItem.getPerson(1),
                            PersonItem.getPerson(2),
                            PersonItem.getPerson(3),
                            PersonItem.getPerson(4),
                            PersonItem.getPerson(5)
                    ),
                    4.3
            ),
            Restaurant(
                    RestaurantSection.TEMPATNYA_INSTAGRAMMABLE,
                    3,
                    CuisineItem.getCuisine(CuisineType.FASTFOOD),
                    R.drawable.img_restaurant_1,
                    4.3,
                    listOf(
                            TypeItem.getType(TypeEnum.NASIAYAM),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Milk and Madu Cafe",
                    listOf(
                            PersonItem.getPerson(1),
                            PersonItem.getPerson(2),
                            PersonItem.getPerson(3),
                            PersonItem.getPerson(4),
                            PersonItem.getPerson(5)
                    ),
                    4.3
            ),
            Restaurant(
                    RestaurantSection.TEMPATNYA_INSTAGRAMMABLE,
                    3,
                    CuisineItem.getCuisine(CuisineType.LOKAL),
                    R.drawable.img_restaurant_1,
                    4.3,
                    listOf(
                            TypeItem.getType(TypeEnum.NASIAYAM),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Starbucks Coffee",
                    listOf(
                            PersonItem.getPerson(1),
                            PersonItem.getPerson(2),
                            PersonItem.getPerson(3),
                            PersonItem.getPerson(4),
                            PersonItem.getPerson(5)
                    ),
                    4.3
            ),
            Restaurant(
                    RestaurantSection.TEMPATNYA_INSTAGRAMMABLE,
                    3,
                    CuisineItem.getCuisine(CuisineType.LOKAL),
                    R.drawable.img_restaurant_1,
                    4.3,
                    listOf(
                            TypeItem.getType(TypeEnum.NASIAYAM),
                            TypeItem.getType(TypeEnum.LOKAL)
                    ),
                    "Folk Pool & Gardens",
                    listOf(
                            PersonItem.getPerson(1),
                            PersonItem.getPerson(2),
                            PersonItem.getPerson(3),
                            PersonItem.getPerson(4),
                            PersonItem.getPerson(5)
                    ),
                    4.3
            )
    )

    private val _restaurants: MutableLiveData<List<Restaurant>> = MutableLiveData()

    fun getRestaurants(section: RestaurantSection, key: Cuisine?): LiveData<List<Restaurant>> {
        return when (key?.key) {
            CuisineType.SEMUA -> {
                Transformations.map(_restaurants) { restaurant ->
                    restaurant.filter { it.section == section}.toList()
                }
            }
            else -> {
                Transformations.map(_restaurants) { restaurant ->
                    restaurant.filter { it.section == section && it.cuisine == key }.toList()
                }
            }
        }
    }

    init {
        _restaurants.value = restaurants
    }
}