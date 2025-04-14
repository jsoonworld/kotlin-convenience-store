package store

import camp.nextstep.edu.missionutils.DateTimes
import store.domain.*
import store.dto.PurchaseItem
import store.parser.ProductParser
import store.parser.PromotionParser
import store.parser.PurchaseParser
import store.parser.SimpleProductFormatter
import store.reader.ProductFileLoader
import store.reader.PromotionFileLoader
import store.service.*
import store.view.InputView
import store.view.OutputView
import java.time.LocalDate

class ConvenienceStoreApp {
    private val outputView = OutputView()
    private val inputView = InputView()
    private val formatter = SimpleProductFormatter()
    private val calculator = DiscountCalculator()

    private val products = loadProducts()
    private val promotionPolicy = loadPromotionPolicy()
    private val promotionInteractor = PromotionInteractor(outputView, inputView)
    private val purchaseCoordinator = PurchaseCoordinator(products, promotionPolicy, promotionInteractor)
    private val stockValidator = StockValidator(products)

    fun run() {
        while (true) {
            printInitialView()
            val purchaseItems = readPurchaseItems() ?: continue

            val error = stockValidator.validate(purchaseItems)
            if (error != null) {
                println(error)
                continue
            }

            val membership = askMembership()
            val receipt = purchaseCoordinator.purchase(purchaseItems)
            val summary = calculator.calculate(receipt.paidItems, receipt.giftItems, membership)

            outputView.printReceipt(summary)
            outputView.printAdditionalPurchaseQuestion()
            if (inputView.readContinueChoice() == "N") {
                println("감사합니다.")
                break
            }
        }
    }

    private fun loadProducts(): Products {
        val parser = ProductParser()
        val loader = ProductFileLoader("src/main/resources/products.md", parser)
        return Products(loader.load())
    }

    private fun loadPromotionPolicy(): PromotionPolicy {
        val parser = PromotionParser()
        val loader = PromotionFileLoader("src/main/resources/promotions.md", parser)
        val today = LocalDate.from(DateTimes.now())
        return PromotionPolicy(today, loader.load())
    }

    private fun printInitialView() {
        outputView.printWelcomeMessage()
        outputView.printStockConditionMessage()
        outputView.printProducts(products.includeMissingZeroStock(), formatter)
        outputView.printPurchaseIntroductionMessage()
    }

    private fun readPurchaseItems(): List<PurchaseItem>? {
        val purchaseInput = inputView.readPurchaseProductAndAccount()
        return try {
            PurchaseParser().parse(purchaseInput)
        } catch (e: IllegalArgumentException) {
            println(e.message)
            null
        }
    }

    private fun askMembership(): Membership {
        outputView.printMembershipDiscountIntroductionMessage()
        return Membership(inputView.readMembershipDiscountChoice() == "Y")
    }
}