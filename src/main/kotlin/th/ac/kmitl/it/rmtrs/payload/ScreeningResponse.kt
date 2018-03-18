package th.ac.kmitl.it.rmtrs.payload

import java.time.LocalDate
import java.time.LocalDateTime

data class ScreeningResponse(
        var id: Long,
        var showDate: LocalDate,
        var showTime: LocalDateTime
)
