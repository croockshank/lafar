package com.genadidharma.lafar.data

import com.genadidharma.lafar.R

object PersonItem {
    val persons = mapOf(
            1 to Person("Rimadani Lia", R.drawable.avatar_1),
            2 to Person("John Steve", R.drawable.avatar_2),
            3 to Person("Rian Sebastian", R.drawable.avatar_3),
            4 to Person("Robert George", R.drawable.avatar_4),
            5 to Person("Meira Rose", R.drawable.avatar_5),
            6 to Person("Abraham Kweli", R.drawable.avatar_6)
    )

    fun getPerson(key: Int): Person?{
        return persons[key]
    }
}