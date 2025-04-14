package store.parser

import store.domain.Promotion
import java.time.LocalDate

class PromotionParser {
    fun parse(line: String): Promotion {
        val tokens = line.split(",")
        val name = tokens[0].trim()
        val buy = tokens[1].trim().toInt()
        val get = tokens[2].trim().toInt()
        val startDate = LocalDate.parse(tokens[3].trim())
        val endDate = LocalDate.parse(tokens[4].trim())
        return Promotion(name, buy, get, startDate, endDate)
    }
}
