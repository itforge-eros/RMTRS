package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import th.ac.kmitl.it.rmtrs.payload.SeatTypeRequest
import th.ac.kmitl.it.rmtrs.service.SeatTypeService
import javax.validation.Valid

@RestController
@RequestMapping("/seattype")
class SeatTypeController(val seatTypeService: SeatTypeService) {

    @GetMapping
    fun getAll() = ResponseEntity.ok(seatTypeService.getAll())

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long) = ResponseEntity.ok(seatTypeService.get(id))

    @PostMapping
    fun add(@Valid @RequestBody seatTypeRequest: SeatTypeRequest): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(seatTypeService.add(seatTypeRequest))
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody seatTypeRequest: SeatTypeRequest, @PathVariable("id") id: Long): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(seatTypeService.update(id, seatTypeRequest))
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        seatTypeService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }
}