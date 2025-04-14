package store.domain

import java.time.LocalDate

class Promotions(private val items: List<Promotion>) {

    fun findByName(name: String): List<Promotion> {
        return items.filter { it.name == name }
    }

    fun findActivePromotion(name: String, today: LocalDate): Promotion? {
        return findByName(name).firstOrNull { it.isActive(today) }
    }
}
