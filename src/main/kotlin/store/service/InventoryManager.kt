package store.service

import store.domain.DeductionResult
import store.domain.Product

class InventoryManager(
    private val candidates: List<Product>
) {

    fun deduct(purchaseQuantity: Int, freeQuantity: Int): DeductionResult {
        val sorted = candidates.sortedBy { it.promotionName.isPresent().not() }
        val mutableList = sorted.toMutableList()

        val paid = mutableList.extractQuantity(purchaseQuantity)
        val gift = mutableList.extractQuantity(freeQuantity)

        val totalPrice = paid.sumOf { it.price * it.quantity }

        return DeductionResult(
            paidProducts = paid,
            totalPaidQuantity = purchaseQuantity,
            totalPrice = totalPrice,
            giftProduct = gift.firstOrNull(),
            giftQuantity = gift.sumOf { it.quantity }
        )
    }

    private fun MutableList<Product>.extractQuantity(requested: Int): List<Product> {
        val result = mutableListOf<Product>()
        var remain = requested

        val iterator = this.listIterator()
        while (iterator.hasNext() && remain > 0) {
            val product = iterator.next()
            val available = product.quantity
            val take = minOf(remain, available)

            result.add(product.copy(quantity = take))
            iterator.set(product.copy(quantity = available - take))

            remain -= take
        }

        if (remain > 0) {
            throw IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.")
        }

        return result
    }
}
