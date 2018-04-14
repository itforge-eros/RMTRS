package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import th.ac.kmitl.it.rmtrs.model.Movie
import th.ac.kmitl.it.rmtrs.model.Screening
import th.ac.kmitl.it.rmtrs.model.Theatre
import th.ac.kmitl.it.rmtrs.payload.PagedResponse
import th.ac.kmitl.it.rmtrs.payload.ScreeningRequest
import th.ac.kmitl.it.rmtrs.service.ScreeningService
import th.ac.kmitl.it.rmtrs.util.validateDate
import javax.validation.Valid

@RestController
@RequestMapping("/screening")
class ScreeningController(val screeningService: ScreeningService) {

    @GetMapping("")
    fun getAvailableScreening(
            @RequestParam(value = "date", defaultValue = "none") dateStr: String,
            @RequestParam("movie") movieIdsStr: String
    ): ResponseEntity<List<Map<String, Any>>> {
        val date = validateDate(dateStr)
        val movieId = movieIdsStr.toLong()
        return screeningService.getScreeningsByMovieIdsAndDate(movieId, date)
                .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long) = screeningService.get(id)

    @PostMapping
    fun add(@Valid @RequestBody req: ScreeningRequest): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(screeningService.add(req))
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody req: ScreeningRequest, @PathVariable("id") id: Long): ResponseEntity<Map<String, Any>> {
        return ResponseEntity.ok(screeningService.update(req, id))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> {
        screeningService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("paged")
    fun getPagedMovies(@RequestParam(value = "page", defaultValue = "0") page: Int): ResponseEntity<PagedResponse<Map<String, Any>>> {
        return ResponseEntity.ok(screeningService.getPaged(page, PagedResponse.PAGE_SIZE))
    }

}