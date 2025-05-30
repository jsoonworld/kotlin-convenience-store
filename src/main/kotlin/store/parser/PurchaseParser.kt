package store.parser

import store.dto.PurchaseItem

class PurchaseParser {
    fun parse(input: String): List<PurchaseItem> {
        if (input.isBlank()) throw IllegalArgumentException("[ERROR] 빈값입니다.")

        return input.trim()
            .removeSuffix(",")
            .split(",")
            .map { segment ->
                if (!segment.startsWith("[") || !segment.endsWith("]")) {
                    throw IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.")
                }

                val trimmed = segment.removePrefix("[").removeSuffix("]")
                val delimiterIndex = trimmed.indexOf("-")

                if (delimiterIndex == -1) {
                    throw IllegalArgumentException("[ERROR] 하이픈으로 상품과 수량을 구분해주세요")
                }

                val name = trimmed.substring(0, delimiterIndex).trim()
                val quantityStr = trimmed.substring(delimiterIndex + 1).trim()
                val quantity = quantityStr.toIntOrNull()
                    ?: throw IllegalArgumentException("[ERROR] 수량을 입력해주세요.")

                if (name.isEmpty()) {
                    throw IllegalArgumentException("[ERROR] 상품 이름은 빈값일 수 없습니다.")
                }

                if (quantity < 1) {
                    throw IllegalArgumentException("[ERROR] 수량은 음수일 수 없습니다.")
                }

                PurchaseItem(name, quantity)
            }
    }
}

