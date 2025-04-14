package store.parser

import store.domain.Product
import java.text.NumberFormat
import java.util.Locale

class SimpleProductFormatter : ProductFormatter {
    override fun format(product: Product): String {
        val price = NumberFormat.getNumberInstance(Locale.KOREA).format(product.price)
        val quantity = "${product.quantity}개"
        val base = "- ${product.name} ${price}원 $quantity"
        return if (product.promotionName.isPresent()) "$base ${product.promotionName}" else base
    }
}