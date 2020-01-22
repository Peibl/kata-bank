package infrastructure

import domain.Account
import domain.AccountId

interface Accounts {
    fun findById(id: AccountId): Account
    fun add(account: Account)
    fun update(account: Account)
}
