package infrastructure

import domain.AccountId
import domain.AccountMovement
import domain.AccountMovementId

class InMemoryAccountMovements : AccountMovements {
    private val id = 1;
    override fun nextId(): AccountMovementId {
        return AccountMovementId(id)
    }

    private val movements = mutableListOf<AccountMovement>()
    override fun add(accountMovement: AccountMovement) {
        this.movements.add(accountMovement)
    }

    override fun findByAccountId(accountId: AccountId) = this.movements.filter { it.accountId == accountId }

}
