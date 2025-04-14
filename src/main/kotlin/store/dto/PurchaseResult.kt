package store.dto

import store.domain.Product

data class PurchaseResult(
    val paidItems: List<Product>,
    val giftItems: List<Product>
)
