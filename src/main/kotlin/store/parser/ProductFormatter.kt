package store.parser

import store.domain.Product

interface ProductFormatter {
    fun format(product: Product): String
}
