package domain

import java.time.LocalDateTime

class AccountSnapshot(
    val accountSnapshotId: AccountSnapshotId,
    val parcialValue: Money,
    val creationDate: LocalDateTime,
    val accountId: AccountId
)
