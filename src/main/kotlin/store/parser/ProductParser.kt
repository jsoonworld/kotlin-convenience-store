package store.parser

import store.domain.Product
import store.domain.PromotionName

class ProductParser {
    fun parse(line: String): Product{
        val tokens = line.split(",")
        require(tokens.size == 4) { "형식이 맞지 않습니다."}

        val name = tokens[0]
        val price = tokens[1].toInt()
        val quantity = tokens[2].toInt()
        val promotionName = PromotionName.from(tokens[3])

        return Product(name, price, quantity, promotionName)
    }
}