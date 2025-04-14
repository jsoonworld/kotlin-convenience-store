package store.domain

data class Product(
    val name: String,
    val price: Int,
    val quantity: Int,
    val promotionName: PromotionName
) {
}