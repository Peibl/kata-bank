package infrastructure

import domain.AccountId
import domain.AccountMovement
import domain.AccountMovementId

interface AccountMovements {
    fun nextId(): AccountMovementId
    fun add(accountMovement: AccountMovement)
    fun findByAccountId(accountId: AccountId): List<AccountMovement>
}
