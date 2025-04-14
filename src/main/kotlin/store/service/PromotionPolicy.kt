package store.service

import store.domain.Promotion
import store.domain.Promotions
import java.time.LocalDate

class PromotionPolicy(
    private val today: LocalDate,
    private val promotions: Promotions
) {

    fun findApplicablePromotion(productName: String, quantity: Int): Promotion? {
        return promotions.findActivePromotion(productName, today)
            ?.takeIf { it.isBuyable(quantity) }
    }

    fun calculateFreeQuantity(productName: String, quantity: Int): Int {
        val promotion = findApplicablePromotion(productName, quantity)
        return promotion?.freeQuantity(quantity) ?: 0
    }

    fun shouldAskToAddMore(productName: String, quantity: Int): Boolean {
        val promotion = promotions.findActivePromotion(productName, today)
        return promotion != null && !promotion.isBuyable(quantity)
    }
}
