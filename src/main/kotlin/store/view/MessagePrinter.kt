package store.view

class MessagePrinter {
    fun printWelcomeMessage() = println("안녕하세요. W편의점입니다.")

    fun printStockConditionMessage() = println("현재 보유하고 있는 상품입니다.")

    fun printPurchaseIntroductionMessage() = println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])")

    fun printMembershipDiscountIntro() = println("멤버십 할인을 받으시겠습니까? (Y/N)")

    fun printReceiptHeader() = println("===========W 편의점=============")

    fun printGiftHeader() = println("===========증\t정=============")

    fun printNoGift() = println("(없음)")

    fun printSummary(totalQuantity: Int, totalPrice: String, promotionDiscount: String, membershipDiscount: String, finalPrice: String) {
        println("==============================")
        println("총구매액\t\t$totalQuantity\t$totalPrice")
        println("행사할인\t\t\t-$promotionDiscount")
        println("멤버십할인\t\t\t-$membershipDiscount")
        println("내실돈\t\t\t $finalPrice")
    }

    fun printAdditionalPurchaseQuestion() = println("감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)")
}