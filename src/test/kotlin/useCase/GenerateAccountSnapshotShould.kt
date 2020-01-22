package useCase


import domain.*
import infrastructure.ClockWithFixedDate
import infrastructure.InMemoryAccountMovements
import infrastructure.InMemoryAccountSnapshots
import infrastructure.InMemoryAccounts
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime
import java.time.Month

class GenerateAccountSnapshotShould {
    @Test
    fun `xxx`() {
        clock.date = SOME_DATE
        accounts.add(Account(SOME_ACCOUNT_ID, INITIAL_BALANCE))

        accountsSnapshots.add(AccountSnapshot(AccountSnapshotId(150), SOME_MONEY, SOME_DATE, SOME_ACCOUNT_ID))

        generateAccountSnapshot().execute(SOME_ACCOUNT_ID)

        val lastSnapshot = accountsSnapshots.findByLastSnapshotFor(SOME_ACCOUNT_ID)
        assertThat(lastSnapshot.creationDate).isEqualTo(SOME_DATE)

    }

    private fun generateAccountSnapshot(): GenerateAccountSnapshot {
        return GenerateAccountSnapshot(accounts, accountsSnapshots, clock);
    }
    val SOME_MONEY = Money(500.0)
    private val accounts = InMemoryAccounts()
    private val accountsSnapshots = InMemoryAccountSnapshots()
    private val SOME_DATE = LocalDateTime.of(2016, Month.APRIL, 15, 3, 15)
    private var clock = ClockWithFixedDate(SOME_DATE)
    private val SOME_ACCOUNT_ID = AccountId("someId")
    private val INITIAL_BALANCE = Money(0.0)
}
