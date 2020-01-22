package infrastructure

import java.time.LocalDateTime

interface Clock {
    fun getCurrentDate(): LocalDateTime
}
