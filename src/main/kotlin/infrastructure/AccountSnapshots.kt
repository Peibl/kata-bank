package infrastructure

import domain.AccountId
import domain.AccountSnapshot
import domain.AccountSnapshotId

interface AccountSnapshots {
    fun findByLastSnapshotFor(id: AccountId): AccountSnapshot
    fun add(accountSnapshot: AccountSnapshot)
    fun nextId(): AccountSnapshotId
}
