package useCase


import domain.*
import infrastructure.InMemoryAccountMovements
import infrastructure.InMemoryAccounts
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month

class GetAccountStatementShould {

    @Test
    fun `return one movement for account with only one deposit`() {
        val account = Account(SOME_ACCOUNT_ID, INITIAL_BALANCE)
        accounts.add(account)
        val accountMovement = AccountMovement(SOME_ACCOUNT_MOVEMENT_ID, SOME_MONEY, May4of2014, SOME_ACCOUNT_ID)
        accountMovements.add(accountMovement)

        val accountMovements = getAccountStatement().execute(SOME_ACCOUNT_ID)

        assertThat(accountMovements.size).isEqualTo(1)
        assertThat(accountMovements[0].amount).isEqualTo(SOME_MONEY)
        assertThat(accountMovements[0].creationDate).isEqualTo(May4of2014)
        assertThat(accountMovements[0].balance).isEqualTo(INITIAL_BALANCE + SOME_MONEY)
    }

    @Test
    fun `return movements sorted by date descending`() {
        val account = Account(SOME_ACCOUNT_ID, INITIAL_BALANCE)
        accounts.add(account)
        accountMovements.add(AccountMovement(OTHER_ACCOUNT_MOVEMENT_ID, SOME_MONEY, December12of2015, SOME_ACCOUNT_ID))
        accountMovements.add(AccountMovement(SOME_ACCOUNT_MOVEMENT_ID, SOME_MONEY, May4of2014, SOME_ACCOUNT_ID))

        val accountMovements = getAccountStatement().execute(SOME_ACCOUNT_ID)

        assertThat(accountMovements.size).isEqualTo(2)
        assertThat(accountMovements[0].creationDate).isEqualTo(December12of2015)
        assertThat(accountMovements[1].creationDate).isEqualTo(May4of2014)
    }

    @Test
    fun `return parcial balance for ech account movements`() {
        val account = Account(SOME_ACCOUNT_ID, INITIAL_BALANCE)
        accounts.add(account)
        accountMovements.add(AccountMovement(SOME_ACCOUNT_MOVEMENT_ID, SOME_MONEY, May4of2014, SOME_ACCOUNT_ID))
        accountMovements.add(AccountMovement(OTHER_ACCOUNT_MOVEMENT_ID, OTHER_MONEY, December12of2015, SOME_ACCOUNT_ID))

        val accountMovements = getAccountStatement().execute(SOME_ACCOUNT_ID)

        assertThat(accountMovements[0].balance).isEqualTo(INITIAL_BALANCE + SOME_MONEY + OTHER_MONEY)
        assertThat(accountMovements[1].balance).isEqualTo(INITIAL_BALANCE + SOME_MONEY)
    }

    private fun getAccountStatement(): GetAccountStatement {
        return GetAccountStatement(accounts, accountMovements)
    }

    private val accounts = InMemoryAccounts()
    private val accountMovements = InMemoryAccountMovements()
    private val SOME_ACCOUNT_ID = AccountId("someId")
    private val SOME_ACCOUNT_MOVEMENT_ID = AccountMovementId(15)
    private val OTHER_ACCOUNT_MOVEMENT_ID = AccountMovementId(39)
    private val SOME_MONEY = Money(1000.0)
    private val OTHER_MONEY = Money(-500.0)
    private val `May4of2014` = LocalDateTime.of(2014, Month.MAY, 4, 10, 10, 30);
    private val `December12of2015` = LocalDateTime.of(2015, Month.DECEMBER, 12, 10, 10, 30);
    private val INITIAL_BALANCE = Money(50.0)
}
