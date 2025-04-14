package store.service

import store.domain.Membership
import store.domain.Product
import store.domain.PurchaseSummary

class DiscountCalculator {

    fun calculate(
        paidProducts: List<Product>,
        giftProducts: List<Product>,
        membership: Membership
    ): PurchaseSummary {
        val totalOriginalPrice = paidProducts.sumOf { it.price * it.quantity }
        val giftValue = giftProducts.sumOf { it.price * it.quantity }
        val totalQuantity = paidProducts.sumOf { it.quantity } + giftProducts.sumOf { it.quantity }

        val promotionDiscount = giftValue
        val membershipDiscount = membership.calculateDiscount(totalOriginalPrice - promotionDiscount)
        val finalPrice = totalOriginalPrice - promotionDiscount - membershipDiscount

        return PurchaseSummary(
            purchasedItems = paidProducts,
            giftItems = giftProducts,
            totalQuantity = totalQuantity,
            totalOriginalPrice = totalOriginalPrice,
            promotionDiscount = promotionDiscount,
            membershipDiscount = membershipDiscount,
            finalPrice = finalPrice
        )
    }
}
