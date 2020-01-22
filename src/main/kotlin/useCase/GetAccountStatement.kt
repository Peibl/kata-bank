package useCase

import domain.AccountId
import domain.AccountMovement
import infrastructure.AccountMovements
import infrastructure.Accounts

class GetAccountStatement(private val accounts: Accounts, private val accountMovements: AccountMovements) {
    fun execute(accountId: AccountId): List<AccountMovement> {
        val account = accounts.findById(accountId)
        var partialBalance = account.initialValue;
        val accountMovements = accountMovements.findByAccountId(accountId)
        accountMovements.forEach {
            partialBalance += it.amount
            it.balance = partialBalance
        }
        return accountMovements.sortedByDescending { it.creationDate }
    }
}
