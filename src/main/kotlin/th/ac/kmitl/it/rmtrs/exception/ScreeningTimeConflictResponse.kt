package th.ac.kmitl.it.rmtrs.exception

import th.ac.kmitl.it.rmtrs.model.Screening
import java.time.LocalDateTime

class ScreeningTimeConflictResponse(
        val timestamp: String = LocalDateTime.now().toString(),
        val message: String,
        val screening: Map<String, Any>
)