package domain

import java.time.LocalDateTime

class AccountMovement(
    private val accountMovementId: AccountMovementId,
    val amount: Money,
    val creationDate: LocalDateTime,
    val accountId: AccountId,
    var balance: Money = Money(0.0)
)
