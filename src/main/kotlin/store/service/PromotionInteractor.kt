package store.service

import store.view.InputView
import store.view.OutputView

class PromotionInteractor(
    private val outputView: OutputView,
    private val inputView: InputView
) {
    fun askToAddFreeItem(productName: String, freeQty: Int): Boolean {
        outputView.printFreeGiftSuggestion(productName, freeQty)
        return inputView.readYesOrNo() == "Y"
    }

    fun encourageToBuyMore(productName: String, requiredQty: Int) {
        outputView.printEncourageToBuyMore(productName, requiredQty)
    }
}