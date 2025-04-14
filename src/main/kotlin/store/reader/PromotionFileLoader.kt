package store.reader

import camp.nextstep.edu.missionutils.Console
import store.domain.Promotions
import store.parser.PromotionParser
import java.io.File

class PromotionFileLoader(
    private val filePath: String,
    private val parser: PromotionParser
) {
    fun load(): Promotions {
        val lines = File(filePath).readLines().drop(1)
        val promotions = lines.map { parser.parse(it) }
        return Promotions(promotions)
    }
}
