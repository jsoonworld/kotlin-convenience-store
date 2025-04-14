package store.domain

data class DeductionResult(
    val paidProducts: List<Product>,
    val totalPaidQuantity: Int,
    val totalPrice: Int,
    val giftProduct: Product?,
    val giftQuantity: Int
)
