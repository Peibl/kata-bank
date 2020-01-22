package infrastructure

import domain.Account
import domain.AccountId

class InMemoryAccounts: Accounts {
    private val accounts = mutableListOf<Account>()
    override fun findById(id: AccountId): Account = accounts.single { it.accountId == id }
    override fun add(account: Account) { this.accounts.add(account) }
    override fun update(account: Account) {
        // update
    }
}
