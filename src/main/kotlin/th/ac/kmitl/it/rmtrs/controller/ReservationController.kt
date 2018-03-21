package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import th.ac.kmitl.it.rmtrs.payload.ReservationRequest
import th.ac.kmitl.it.rmtrs.service.ReservationService
import th.ac.kmitl.it.rmtrs.util.toReservationWithDetail
import javax.validation.Valid
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/reserve")
class ReservationController(val reservationService: ReservationService) {

    @PostMapping
    fun reserve(@Valid @RequestBody req: ReservationRequest): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(reservationService.reserve(req))
    }

    @GetMapping("/{id}")
    fun get(@PathParam("id") id: Long)
            = reservationService.checkIfExisted(id).toReservationWithDetail()

    @GetMapping("checkin/{id}")
    fun checkIn(@PathParam("id") id: Long)
            =



}