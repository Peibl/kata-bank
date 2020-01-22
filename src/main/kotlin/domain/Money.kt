package domain

class Money(val amount: Double) {
    operator fun plus(money: Money) = Money(amount + money.amount)
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Money

        if (amount != other.amount) return false

        return true
    }

    override fun hashCode(): Int {
        return amount.hashCode()
    }

    override fun toString(): String {
        return amount.toString()
    }

}
