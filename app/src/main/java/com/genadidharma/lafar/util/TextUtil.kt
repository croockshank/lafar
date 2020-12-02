package com.genadidharma.lafar.util

import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class TextUtil {
    companion object {
        @JvmStatic
        fun formatRupiah(money: Long, simplified: Boolean): String {
            if (simplified) {
                val dec = DecimalFormat("#.#")
                val symbol: String
                var moneyFormatted = ""
                when {
                    money < 900 -> {
                        dec.format(money)
                        symbol = ""
                    }
                    money < 900000 -> {
                        moneyFormatted = dec.format(money / 1000)
                        symbol = "rb"
                    }
                    money < 900000000 -> {
                        moneyFormatted = dec.format(money / 1000000)
                        symbol = "jt"
                    }
                    money < 900000000000 -> {
                        moneyFormatted = dec.format(money / 1000000000)
                        symbol = "M"
                    }
                    else -> {
                        moneyFormatted = dec.format(money / 1000000000000)
                        symbol = "T"
                    }
                }
                return "Rp$moneyFormatted$symbol"
            }
            val locale = Locale("in", "ID")
            val numberFormat = NumberFormat.getCurrencyInstance(locale)
            return numberFormat.format(money).replace(",00", "")
        }
    }
}