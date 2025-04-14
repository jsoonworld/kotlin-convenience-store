package store.service

import store.domain.Products
import store.dto.PurchaseItem

class StockChecker(private val products: Products) {

    fun validate(purchaseItems: List<PurchaseItem>) {
        purchaseItems.forEach { item ->
            if (!products.contain(item.name)) {
                throw IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. ${item.name}")
            }

            val total = products.totalQuantityOf(item.name)
            if (item.quantity > total) {
                throw IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.")
            }
        }
    }
}