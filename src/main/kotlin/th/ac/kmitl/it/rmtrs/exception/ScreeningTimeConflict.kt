package th.ac.kmitl.it.rmtrs.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import th.ac.kmitl.it.rmtrs.model.Screening

@ResponseStatus(value = HttpStatus.CONFLICT)
class ScreeningTimeConflict(message: String, val screening: Screening) : RuntimeException(message)
