package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import th.ac.kmitl.it.rmtrs.model.Movie
import th.ac.kmitl.it.rmtrs.payload.PagedResponse
import th.ac.kmitl.it.rmtrs.payload.ReservationRequest
import th.ac.kmitl.it.rmtrs.service.ReservationService
import th.ac.kmitl.it.rmtrs.util.toReservationWithDetail
import javax.validation.Valid

@RestController
@RequestMapping("/reserve")
class ReservationController(val reservationService: ReservationService) {

    @PostMapping
    fun reserve(@Valid @RequestBody req: ReservationRequest): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(reservationService.reserve(req))
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long)
            = ResponseEntity.ok(reservationService.checkIfExisted(id).toReservationWithDetail())

    @GetMapping("checkin/{id}")
    fun checkIn(@PathVariable("id") id: Long)
            = reservationService.checkIn(id)

    @GetMapping("paged")
    fun getPaged(@RequestParam(value = "page", defaultValue = "0") page: Int): ResponseEntity<PagedResponse<Map<String, Any>>> {
        return ResponseEntity.ok(reservationService.getPaged(page, PagedResponse.PAGE_SIZE))
    }
}