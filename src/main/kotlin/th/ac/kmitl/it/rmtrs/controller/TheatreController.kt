package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import th.ac.kmitl.it.rmtrs.model.Movie
import th.ac.kmitl.it.rmtrs.payload.PagedResponse
import th.ac.kmitl.it.rmtrs.payload.TheatreRequest
import th.ac.kmitl.it.rmtrs.service.TheatreService
import javax.validation.Valid

@RestController
@RequestMapping("/theatre")
class TheatreController(val theatreService: TheatreService) {

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long) = ResponseEntity.ok(theatreService.get(id))

    @PostMapping
    fun add(@Valid @RequestBody theatreRequest: TheatreRequest): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(theatreService.add(theatreRequest))
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody theatreRequest: TheatreRequest, @PathVariable("id") id: Long): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(theatreService.update(id, theatreRequest))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Unit> {
        theatreService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("paged")
    fun getPaged(@RequestParam(value = "page", defaultValue = "0") page: Int): ResponseEntity<PagedResponse<Map<String, Any>>> {
        return ResponseEntity.ok(theatreService.getPaged(page, PagedResponse.PAGE_SIZE))
    }
}
