package infrastructure

import domain.AccountId
import domain.AccountMovementId
import domain.AccountSnapshot
import domain.AccountSnapshotId

class InMemoryAccountSnapshots: AccountSnapshots {
    private val id = 1;
    override fun nextId(): AccountSnapshotId {
        return AccountSnapshotId(id)
    }
    private val accountsSnapshots = mutableListOf<AccountSnapshot>()
    override fun findByLastSnapshotFor(id: AccountId) =accountsSnapshots.filter { it.accountId == id }.sortedByDescending { it.creationDate }[0]
    override fun add(accountSnapshot: AccountSnapshot) { this.accountsSnapshots.add(accountSnapshot) }
}
