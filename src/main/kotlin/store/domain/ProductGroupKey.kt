package store.domain

data class ProductGroupKey(
    val name: String,
    val price: Int,
    val promotionName: PromotionName
) {
    constructor(product: Product) : this(product.name, product.price, product.promotionName)
}
