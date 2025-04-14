package store.view

import store.domain.Product
import store.domain.PurchaseSummary
import store.parser.ProductFormatter
import java.text.NumberFormat
import java.util.*

class OutputView {
    private val formatter = NumberFormat.getNumberInstance(Locale.KOREA)
    private val messagePrinter = MessagePrinter()

    fun printWelcomeMessage() = messagePrinter.printWelcomeMessage()

    fun printStockConditionMessage() = messagePrinter.printStockConditionMessage()

    fun printProducts(products: List<Product>, formatter: ProductFormatter) {
        products
            .sortedWith(compareBy({ it.name }, { it.price }, { it.promotionName.toString() }))
            .forEach { product ->
                println(formatter.format(product))
            }
    }

    fun printPurchaseIntroductionMessage() = messagePrinter.printPurchaseIntroductionMessage()

    fun printMembershipDiscountIntroductionMessage() = messagePrinter.printMembershipDiscountIntro()

    fun printReceipt(summary: PurchaseSummary) {
        messagePrinter.printReceiptHeader()
        summary.purchasedItems.forEach {
            println("${it.name}\t\t${it.quantity}\t${formatter.format(it.price * it.quantity)}")
        }

        messagePrinter.printGiftHeader()
        if (summary.giftItems.isEmpty()) {
            messagePrinter.printNoGift()
        } else {
            summary.giftItems.forEach {
                println("${it.name}\t\t${it.quantity}")
            }
        }

        messagePrinter.printSummary(
            totalQuantity = summary.totalQuantity,
            totalPrice = formatter.format(summary.totalOriginalPrice),
            promotionDiscount = formatter.format(summary.promotionDiscount),
            membershipDiscount = formatter.format(summary.membershipDiscount),
            finalPrice = formatter.format(summary.finalPrice)
        )
    }

    fun printAdditionalPurchaseQuestion() = messagePrinter.printAdditionalPurchaseQuestion()

    fun printFreeGiftSuggestion(name: String, freeQty: Int) {
        println("현재 ${name}은(는) ${freeQty}개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)")
    }

    fun printEncourageToBuyMore(name: String, requiredQty: Int) {
        println("현재 ${name}은(는) ${requiredQty}개 이상 구매해야 증정 혜택을 받을 수 있습니다.")
    }
}
