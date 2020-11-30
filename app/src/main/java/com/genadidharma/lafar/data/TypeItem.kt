package com.genadidharma.lafar.data

object TypeItem {
    private val types = mapOf(
            TypeEnum.LOKAL to Type("Lokal"),
            TypeEnum.BEBEK to Type("Bebek"),
            TypeEnum.NASIAYAM to Type("Nasi Ayam")
    )

    fun getType(key: TypeEnum): Type?{
        return types[key]
    }
}