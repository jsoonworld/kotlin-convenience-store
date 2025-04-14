package store.view

class OutputView {
    private val WELCOME_MESSAGE: String = "안녕하세요. W편의점입니다."
    private val STOCK_CONDITION_MESSAGE: String = "현재 보유하고 있는 상품입니다."

    fun printWelcomeMessage() {
        println(WELCOME_MESSAGE)
    }

    fun printStockConditionMessage() {
        println(STOCK_CONDITION_MESSAGE)
    }
}