package useCase

import domain.AccountId
import domain.AccountMovement
import domain.Money
import infrastructure.AccountMovements
import infrastructure.Accounts
import infrastructure.Clock

class MakeDeposit(private val accountMovements: AccountMovements, var clock: Clock) {

    fun execute(accountId: AccountId, money: Money) {
        val accountMovementId = accountMovements.nextId()
        val accountMovement = AccountMovement(accountMovementId, money, clock.getCurrentDate(), accountId)
        accountMovements.add(accountMovement)
    }
}
