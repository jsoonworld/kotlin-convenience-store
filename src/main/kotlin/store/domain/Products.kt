package store.domain

class Products(private val items: List<Product>) {

    fun findByName(name: String): List<Product>{
        return items.filter{ it.name == name}
    }

    fun totalQuantityOf(name: String): Int{
        return findByName(name).sumOf { it.quantity }
    }

    fun contain(name: String): Boolean{
        return items.any{it.name == name}
    }

    fun asList(): List<Product> {
        return items
    }

    fun includeMissingZeroStock(): List<Product> {
        val productGroups = items.groupBy { Triple(it.name, it.price, it.promotionName) }
        val existingZeroStocks = productGroups
            .mapValues { group -> group.value.any { it.quantity == 0 } }

        val missingZeros = productGroups
            .filter { !existingZeroStocks[it.key]!! }
            .map { (key, _) ->
                val (name, price, promotion) = key
                Product(name, price, 0, promotion)
            }

        return items + missingZeros
    }

    fun asListForDisplay(): List<Product> {
        val all = items.toMutableList()

        val grouped = items.groupBy { Triple(it.name, it.price, it.promotionName) }
        grouped.forEach { (key, list) ->
            val totalQty = list.sumOf { it.quantity }
            if (totalQty == 0) {
                all.add(Product(key.first, key.second, 0, key.third))
            }
        }

        return all
    }
}