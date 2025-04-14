package store.domain

class ProductGroup(
    private val products: List<Product>
) {
    fun deduct(purchaseQty: Int, giftQty: Int): DeductionResult {
        val sorted = products.sortedBy { it.promotionName.isPresent().not() }.toMutableList()

        fun extract(requested: Int): List<Product> {
            val result = mutableListOf<Product>()
            var remain = requested

            val iterator = sorted.listIterator()
            while (iterator.hasNext() && remain > 0) {
                val product = iterator.next()
                val available = product.quantity
                val take = minOf(remain, available)

                result.add(product.copy(quantity = take))
                iterator.set(product.copy(quantity = available - take))
                remain -= take
            }

            if (remain > 0) throw IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.")
            return result
        }

        val paid = extract(purchaseQty)
        val gift = extract(giftQty)

        return DeductionResult(
            paidProducts = paid,
            totalPaidQuantity = purchaseQty,
            totalPrice = paid.sumOf { it.price * it.quantity },
            giftProduct = gift.firstOrNull(),
            giftQuantity = gift.sumOf { it.quantity }
        )
    }

    fun totalQuantity(): Int = products.sumOf { it.quantity }

    fun name(): String = products.first().name

    fun hasEnoughStock(requested: Int): Boolean = totalQuantity() >= requested
}