package store.domain

class Membership(private val isMember: Boolean) {
    companion object {
        private const val DISCOUNT_RATE = 0.3
        private const val MAX_DISCOUNT = 8000
    }

    fun calculateDiscount(applicableAmount: Int): Int {
        if (!isMember) return 0
        return (applicableAmount * DISCOUNT_RATE).toInt().coerceAtMost(MAX_DISCOUNT)
    }

    fun isApplied(): Boolean = isMember
}
