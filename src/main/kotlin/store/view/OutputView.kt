package store.view

import store.domain.Product
import store.parser.ProductFormatter

class OutputView {
    private val WELCOME_MESSAGE: String = "안녕하세요. W편의점입니다."
    private val STOCK_CONDITION_MESSAGE: String = "현재 보유하고 있는 상품입니다."
    private val PURCHASE_INTRODUCTION_MESSAGE: String = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"
    private val MEMBERSHIP_DISCOUNT_INTRODUCTION_MESSAGE: String = "멤버십 할인을 받으시겠습니까? (Y/N)"

    fun printWelcomeMessage() {
        println(WELCOME_MESSAGE)
    }

    fun printStockConditionMessage() {
        println(STOCK_CONDITION_MESSAGE)
    }

    fun printProducts(products: List<Product>, formatter: ProductFormatter) {
        products.forEach { product ->
            println(formatter.format(product))
        }
    }

    fun printPurchaseIntroductionMessage() {
        println(PURCHASE_INTRODUCTION_MESSAGE)
    }

    fun printMembershipDiscountIntroductionMessage() {
        println(MEMBERSHIP_DISCOUNT_INTRODUCTION_MESSAGE)
    }
}