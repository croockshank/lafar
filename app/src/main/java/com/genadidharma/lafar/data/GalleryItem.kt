package com.genadidharma.lafar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.genadidharma.lafar.R

object GalleryItem {
    val galleries = listOf(
            Gallery(
                    GalleryType.ALBUM,
                    RestaurantItem.getRestaurant(1),
                    listOf(
                            Asset(
                                    1,
                                    AssetType.IMAGE,
                                    R.drawable.img_restaurant_1,
                                    null
                            ),
                            Asset(
                                    1,
                                    AssetType.VIDEO,
                                    R.drawable.img_restaurant_1,
                                    null
                            ),
                            Asset(
                                    1,
                                    AssetType.IMAGE,
                                    R.drawable.img_restaurant_1,
                                    null
                            )
                    )
            ),
            Gallery(
                    GalleryType.POSTINGAN_TEMAN,
                    RestaurantItem.getRestaurant(1),
                    listOf(
                            Asset(
                                    1,
                                    AssetType.IMAGE,
                                    R.drawable.img_restaurant_1,
                                    PersonItem.getPerson(1)
                            ),
                            Asset(
                                    1,
                                    AssetType.IMAGE,
                                    R.drawable.img_restaurant_1,
                                    PersonItem.getPerson(1)
                            ),
                            Asset(
                                    1,
                                    AssetType.IMAGE,
                                    R.drawable.img_restaurant_1,
                                    PersonItem.getPerson(1)
                            )
                    )
            )
    )

    val _galleries: MutableLiveData<List<Gallery>> = MutableLiveData()

    fun getGalleries(restaurant: Restaurant, type: GalleryType): LiveData<Gallery>? {
        return Transformations.map(_galleries) { gallery ->
            gallery.find {
                it.type == type && it.restaurant == restaurant
            }
        }
    }

    init {
        _galleries.value = galleries
    }
}