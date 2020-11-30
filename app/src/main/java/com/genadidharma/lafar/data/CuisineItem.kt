package com.genadidharma.lafar.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.genadidharma.lafar.R

object CuisineItem {
    private val allCuisines = listOf(
            Cuisine(
                    CuisineType.SEMUA,
                    CuisineType.SEMUA.type,
                    R.drawable.ic_chip_cuisine_semua
            ),
            Cuisine(
                    CuisineType.LOKAL,
                    CuisineType.LOKAL.type,
                    R.drawable.ic_chip_cuisine_lokal
            ),
            Cuisine(
                    CuisineType.SEAFOOD,
                    CuisineType.SEAFOOD.type,
                    R.drawable.ic_chip_cuisine_seafood
            ),
            Cuisine(
                    CuisineType.FASTFOOD,
                    CuisineType.FASTFOOD.type,
                    R.drawable.ic_chip_cuisine_fast_food
            ),
            Cuisine(
                    CuisineType.VEGETARIAN,
                    CuisineType.VEGETARIAN.type,
                    R.drawable.ic_chip_cuisine_vegetarian
            )
    )

    private val _cuisines: MutableLiveData<List<Cuisine>> = MutableLiveData()

    fun getCuisines(): LiveData<List<Cuisine>> {
        return _cuisines
    }

    fun getCuisine(key: CuisineType): Cuisine?{
        val cuisine = allCuisines.map {
            it.key to it
        }.toMap()
        return cuisine[key]
    }

    fun getCuisine(id: Int): Cuisine?{
        val cuisine = allCuisines.map{
            it.key.id to it
        }.toMap()
        return cuisine[id]
    }

    init {
        _cuisines.value = allCuisines
    }
}