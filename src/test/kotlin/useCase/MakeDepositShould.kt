package useCase


import domain.Account
import domain.AccountId
import domain.Money
import infrastructure.ClockWithFixedDate
import infrastructure.InMemoryAccountMovements
import infrastructure.InMemoryAccounts
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month

class MakeDepositShould {
    @Test
    fun `create an account movement for indicated account`() {
        accounts.add(Account(SOME_ACCOUNT_ID, INITIAL_BALANCE))

        makeDeposit().execute(SOME_ACCOUNT_ID, SOME_MONEY)

        val accountMovements = accountsMovements.findByAccountId(SOME_ACCOUNT_ID)
        assertThat(accountMovements.size).isEqualTo(1)
        assertThat(accountMovements[0].amount).isEqualTo(SOME_MONEY)
        assertThat(accountMovements[0].creationDate).isEqualTo(SOME_DATE)
    }

    private fun makeDeposit(): MakeDeposit {
        return MakeDeposit(accountsMovements, clock);
    }

    private val accounts = InMemoryAccounts()
    private val accountsMovements = InMemoryAccountMovements()
    private val SOME_DATE = LocalDateTime.of(2016, Month.APRIL, 15, 3, 15)
    private var clock = ClockWithFixedDate(SOME_DATE)
    private val SOME_ACCOUNT_ID = AccountId("someId")
    private val SOME_MONEY = Money(1000.0)
    private val INITIAL_BALANCE = Money(0.0)
}
