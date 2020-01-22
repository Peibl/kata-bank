package infrastructure

import java.time.LocalDateTime

class ClockWithFixedDate(var date: LocalDateTime): Clock {
    override fun getCurrentDate() = date
}
