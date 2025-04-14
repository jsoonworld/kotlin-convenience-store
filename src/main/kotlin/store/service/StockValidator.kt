package store.service

import store.domain.Products
import store.dto.PurchaseItem

class StockValidator(private val products: Products) {
    fun validate(purchaseItems: List<PurchaseItem>): String? {
        for (item in purchaseItems) {
            if (!products.contain(item.name)) {
                return "[ERROR] 존재하지 않는 상품입니다. ${item.name}"
            }
            if (item.quantity > products.totalQuantityOf(item.name)) {
                return "[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."
            }
        }
        return null
    }
}