package store.view

import camp.nextstep.edu.missionutils.Console

class InputView {
    fun readPurchaseProductAndAccount(): String {
        return Console.readLine()
    }

    fun readMembershipDiscountChoice(): String{
        return Console.readLine()
    }
}