package store.domain

import java.time.LocalDate

data class Promotion(
    val name: String,
    val buy: Int,
    val get: Int,
    val startDate: LocalDate,
    val endDate: LocalDate
) {
    fun isActive(today: LocalDate): Boolean {
        return !today.isBefore(startDate) && !today.isAfter(endDate)
    }

    fun isBuyable(quantity: Int): Boolean {
        return quantity >= buy
    }

    fun freeQuantity(quantity: Int): Int {
        return (quantity / buy) * get
    }
}
