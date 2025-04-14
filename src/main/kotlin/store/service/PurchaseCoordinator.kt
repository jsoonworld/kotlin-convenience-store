package store.service

import store.domain.Product
import store.domain.ProductGroup
import store.domain.Products
import store.dto.PurchaseItem
import store.dto.PurchaseResult

class PurchaseCoordinator(
    private val products: Products,
    private val promotionPolicy: PromotionPolicy,
    private val interactor: PromotionInteractor
) {
    fun purchase(purchaseItems: List<PurchaseItem>): PurchaseResult {
        val paidItems = mutableListOf<Product>()
        val giftItems = mutableListOf<Product>()

        for (item in purchaseItems) {
            val promotion = promotionPolicy.findApplicablePromotion(item.name, item.quantity)
            val freeQty = promotion?.freeQuantity(item.quantity) ?: 0

            if (freeQty > 0) {
                val shouldAddGift = interactor.askToAddFreeItem(item.name, freeQty)
                if (!shouldAddGift) continue
            }

            if (promotionPolicy.shouldAskToAddMore(item.name, item.quantity)) {
                val requiredQty = promotionPolicy.findApplicablePromotion(item.name, 0)?.buy ?: continue
                interactor.encourageToBuyMore(item.name, requiredQty)
            }

            val group = ProductGroup(products.findByName(item.name))
            val result = group.deduct(item.quantity, freeQty)
            paidItems.addAll(result.paidProducts)
            result.giftProduct?.copy(quantity = result.giftQuantity)?.let { giftItems.add(it) }
        }

        return PurchaseResult(paidItems, giftItems)
    }
}