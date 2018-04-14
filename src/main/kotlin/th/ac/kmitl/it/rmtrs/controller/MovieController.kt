package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import th.ac.kmitl.it.rmtrs.model.Movie
import th.ac.kmitl.it.rmtrs.payload.MovieRequest
import th.ac.kmitl.it.rmtrs.payload.PagedResponse
import th.ac.kmitl.it.rmtrs.service.MovieService
import th.ac.kmitl.it.rmtrs.util.validateDate
import javax.validation.Valid

@RestController
@RequestMapping("/movie")
class MovieController(val movieService: MovieService) {

    @GetMapping("/available")
    fun getAvailableMovies(@RequestParam(value = "date", defaultValue = "none") dateStr: String)
            : ResponseEntity<List<Map<String, Any>>> {
        val date = validateDate(dateStr)
        println("Date: $date")
        return movieService.getAllAvailableMoviesWithScreeningAmount(date)
                .let { ResponseEntity.ok(it) }
    }

    @GetMapping("/{id}")
    fun get(
            @PathVariable("id") id: Long,
            @RequestParam(value = "date", defaultValue = "none") dateStr: String
    ): ResponseEntity<Map<String, Any>> {
        val date = validateDate(dateStr)
        return ResponseEntity.ok(movieService.getByDate(id, date))
    }
    @PostMapping
    fun add(@Valid @RequestBody req: MovieRequest)
            = ResponseEntity.ok().body(movieService.add(req))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> {
        movieService.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @GetMapping("paged")
    fun getPagedMovies(@RequestParam(value = "page", defaultValue = "0") page: Int): ResponseEntity<PagedResponse<Movie>> {
        return ResponseEntity.ok(movieService.getPaged(page, PagedResponse.PAGE_SIZE))
    }

}
