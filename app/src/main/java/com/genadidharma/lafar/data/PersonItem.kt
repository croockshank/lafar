package com.genadidharma.lafar.data

import com.genadidharma.lafar.R

object PersonItem {
    val persons = mapOf(
            1 to Person("Rimadani Lia", R.drawable.avatar_1),
            2 to Person("Rimadani Lia", R.drawable.avatar_1),
            3 to Person("Rimadani Lia", R.drawable.avatar_1),
            4 to Person("Rimadani Lia", R.drawable.avatar_1),
    )

    fun getPerson(key: Int): Person?{
        return persons[key]
    }
}