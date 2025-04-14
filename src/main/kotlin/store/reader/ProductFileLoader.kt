package store.reader

import store.domain.Product
import store.parser.ProductParser
import java.io.File

class ProductFileLoader(
    private val filePath: String,
    private val parser: ProductParser
) {
    fun load(): List<Product> {
        return File(filePath)
            .readLines()
            .drop(1)
            .map{parser.parse(it)}
    }
}