package th.ac.kmitl.it.rmtrs.exception

import org.springframework.http.HttpHeaders
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import th.ac.kmitl.it.rmtrs.controller.ScreeningController
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest


@ControllerAdvice(basePackageClasses = [ScreeningController::class])
@RestController
class ScreeningControllerAdvice: ResponseEntityExceptionHandler() {

    @ExceptionHandler(ScreeningTimeConflict::class)
    @ResponseBody
    fun handleScreeningTimeConflict(ex: ScreeningTimeConflict, req: WebRequest): ResponseEntity<*> {
        val errRes = ScreeningTimeConflictResponse(message = ex.message ?: "Default Message", screening = ex.screening)
        return ResponseEntity(errRes, HttpStatus.CONFLICT)
    }
}