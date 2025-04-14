package store.domain

class PromotionName private constructor(private val value: String?) {

    fun isPresent(): Boolean = value != null && value != "null"

    override fun toString(): String = value ?: ""

    companion object {
        fun from(raw: String?): PromotionName = PromotionName(raw?.takeIf { it != "null" && it.isNotBlank() })
    }
}