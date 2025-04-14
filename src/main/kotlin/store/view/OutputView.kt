package store.view

import store.domain.Product
import store.domain.PurchaseSummary
import store.parser.ProductFormatter
import java.text.NumberFormat
import java.util.*

class OutputView {
    private val WELCOME_MESSAGE: String = "안녕하세요. W편의점입니다."
    private val STOCK_CONDITION_MESSAGE: String = "현재 보유하고 있는 상품입니다."
    private val PURCHASE_INTRODUCTION_MESSAGE: String = "구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"
    private val MEMBERSHIP_DISCOUNT_INTRODUCTION_MESSAGE: String = "멤버십 할인을 받으시겠습니까? (Y/N)"
    private val formatter = NumberFormat.getNumberInstance(Locale.KOREA)

    fun printWelcomeMessage() {
        println(WELCOME_MESSAGE)
    }

    fun printStockConditionMessage() {
        println(STOCK_CONDITION_MESSAGE)
    }

    fun printProducts(products: List<Product>, formatter: ProductFormatter) {
        products
            .sortedWith(compareBy({ it.name }, { it.price }, { it.promotionName.toString() }))
            .forEach { product ->
                println(formatter.format(product))
            }
    }

    fun printPurchaseIntroductionMessage() {
        println(PURCHASE_INTRODUCTION_MESSAGE)
    }

    fun printMembershipDiscountIntroductionMessage() {
        println(MEMBERSHIP_DISCOUNT_INTRODUCTION_MESSAGE)
    }

    fun printReceipt(summary: PurchaseSummary) {
        println("===========W 편의점=============")
        println("상품명\t\t수량\t금액")
        summary.purchasedItems.forEach {
            val line = "${it.name}\t\t${it.quantity}\t${formatter.format(it.price * it.quantity)}"
            println(line)
        }

        println("===========증\t정=============")
        if (summary.giftItems.isEmpty()) {
            println("(없음)")
        } else {
            summary.giftItems.forEach {
                println("${it.name}\t\t${it.quantity}")
            }
        }

        println("==============================")
        println("총구매액\t\t${summary.totalQuantity}\t${formatter.format(summary.totalOriginalPrice)}")
        println("행사할인\t\t\t-${formatter.format(summary.promotionDiscount)}")
        println("멤버십할인\t\t\t-${formatter.format(summary.membershipDiscount)}")
        println("내실돈\t\t\t ${formatter.format(summary.finalPrice)}")
    }

    fun printAdditionalPurchaseQuestion() {
        println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
    }

    fun printFreeGiftSuggestion(name: String, freeQty: Int) {
        println("현재 ${name}은(는) ${freeQty}개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)")
    }

    fun printEncourageToBuyMore(name: String, requiredQty: Int) {
        println("현재 ${name}은(는) ${requiredQty}개 이상 구매해야 증정 혜택을 받을 수 있습니다.")
    }
}