package store.domain

data class PurchaseSummary(
    val purchasedItems: List<Product>,
    val giftItems: List<Product>,
    val totalQuantity: Int,
    val totalOriginalPrice: Int,
    val promotionDiscount: Int,
    val membershipDiscount: Int,
    val finalPrice: Int
)
