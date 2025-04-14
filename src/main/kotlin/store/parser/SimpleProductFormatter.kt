package store.parser

import store.domain.Product
import java.text.NumberFormat
import java.util.*

class SimpleProductFormatter : ProductFormatter {
    override fun format(product: Product): String {
        val price = NumberFormat.getNumberInstance(Locale.KOREA).format(product.price)
        val quantity = if (product.quantity == 0) "재고 없음" else "${product.quantity}개"
        val base = "- ${product.name} ${price}원 $quantity"
        return if (product.promotionName.isPresent()) "$base ${product.promotionName}" else base
    }
}
