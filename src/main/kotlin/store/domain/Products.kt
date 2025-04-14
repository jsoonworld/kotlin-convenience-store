package store.domain

class Products(private val items: List<Product>) {
    fun findByName(name: String): List<Product> = items.filter { it.name == name }

    fun totalQuantityOf(name: String): Int = findByName(name).sumOf { it.quantity }

    fun contain(name: String): Boolean = items.any { it.name == name }

    fun includeMissingZeroStock(): List<Product> {
        val grouped = items.groupBy { ProductGroupKey(it) }

        val missingZeros = grouped
            .filterValues { group -> group.none { it.quantity == 0 } }
            .map { (key, _) ->
                Product(key.name, key.price, 0, key.promotionName)
            }

        return items + missingZeros
    }
}