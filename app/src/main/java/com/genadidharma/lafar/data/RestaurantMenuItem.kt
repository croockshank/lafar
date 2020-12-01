package com.genadidharma.lafar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.genadidharma.lafar.R

object RestaurantMenuItem {
    val menus = listOf(
            RestaurantMenu(
                    RestaurantItem.getRestaurant(1),
                    listOf(
                            Menu(
                                    1,
                                    MenuType.BEST_SELLER,
                                    "Nasi Kotak Biasa",
                                    R.drawable.img_restaurant_1,
                                    30000,
                                    "Nasi, ayam betutu, sate, urap, telor, kacang saur, sambel",
                                    listOf(
                                            AddOn(
                                                    "Sate Lilit",
                                                    R.drawable.ic_section_menu,
                                                    20000
                                            ),
                                            AddOn(
                                                    "Sambal",
                                                    R.drawable.ic_section_menu,
                                                    10000
                                            )
                                    )
                            ),
                            Menu(
                                    2,
                                    MenuType.BEST_SELLER,
                                    "Betutu Upih",
                                    R.drawable.img_restaurant_1,
                                    30000,
                                    "1 ekor ayam betutu yang sudah dimasak dan dibumbui",
                                    listOf(
                                            AddOn(
                                                    "Sate Lilit",
                                                    R.drawable.ic_section_menu,
                                                    20000
                                            ),
                                            AddOn(
                                                    "Sambal",
                                                    R.drawable.ic_section_menu,
                                                    10000
                                            )
                                    )
                            ),
                            Menu(
                                    5,
                                    MenuType.PROMO_WEEKEND,
                                    "Ayam Betutu Takilan",
                                    R.drawable.img_restaurant_1,
                                    30000,
                                    "Ayam betutu yang kaya rasa dengan kuah yang gurih, ditemani dengan sate, urap, dan telur asin serta dipadukan dengan nasi dan sambal ",
                                    listOf(
                                            AddOn(
                                                    "Sate Lilit",
                                                    R.drawable.ic_section_menu,
                                                    20000
                                            ),
                                            AddOn(
                                                    "Sambal",
                                                    R.drawable.ic_section_menu,
                                                    10000
                                            )
                                    )
                            )
                    )
            )
    )

    private val _menus: MutableLiveData<List<RestaurantMenu>> = MutableLiveData()

    fun getMenus(restaurant: Restaurant?, type: MenuType?): LiveData<List<Menu>> {
        return Transformations.map(_menus) { menu ->
            menu.find {
                it.restaurant == restaurant
            }?.menu?.filter {
                it.type == type
            }?.toList()
        }
    }

    init {
        _menus.value = menus
    }

}