package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import th.ac.kmitl.it.rmtrs.payload.SeatRequest
import th.ac.kmitl.it.rmtrs.service.SeatService
import javax.validation.Valid

@RestController
@RequestMapping("/seat")
class SeatController(val seatService: SeatService) {

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long) = ResponseEntity.ok(seatService.get(id))

    @PostMapping
    fun add(@Valid @RequestBody seatRequest: SeatRequest): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(seatService.add(seatRequest))
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody seatRequest: SeatRequest, @PathVariable("id") id: Long): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(seatService.update(id, seatRequest))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        seatService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }
}