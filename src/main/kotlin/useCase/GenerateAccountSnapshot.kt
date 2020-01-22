package useCase

import domain.AccountId
import domain.AccountSnapshot
import infrastructure.AccountSnapshots
import infrastructure.Accounts
import infrastructure.Clock

class GenerateAccountSnapshot(val accounts: Accounts, val accountSnapshots: AccountSnapshots, val clock: Clock) {
    fun execute(accountId: AccountId) {
        val lastSnapshot = this.accountSnapshots.findByLastSnapshotFor(accountId)
        val nextId = this.accountSnapshots.nextId()
        val newSnapshot = AccountSnapshot(nextId, lastSnapshot.parcialValue, clock.getCurrentDate(), accountId)
        this.accountSnapshots.add(newSnapshot)
    }

}
