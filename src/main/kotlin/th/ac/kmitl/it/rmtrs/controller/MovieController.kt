package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import th.ac.kmitl.it.rmtrs.payload.AvailableMoviesByDate
import th.ac.kmitl.it.rmtrs.payload.MovieRequest
import th.ac.kmitl.it.rmtrs.payload.MovieResponse
import th.ac.kmitl.it.rmtrs.service.MovieService
import th.ac.kmitl.it.rmtrs.util.toLocalDate
import java.time.LocalDate
import javax.validation.Valid

@RestController
@RequestMapping("/movie")
class MovieController(val movieService: MovieService) {

    @GetMapping("/available")
    fun getAvailableMovies(@RequestParam(value = "date", defaultValue = "none") dateStr: String)
            : ResponseEntity<AvailableMoviesByDate> {
        val date = if(dateStr == "none") LocalDate.now() else dateStr.toLocalDate()
        println("Date: $date")
        return movieService.getAllAvailableMoviesWithScreeningAmount(date)
                .let { ResponseEntity.ok(it) }
    }

    @PostMapping
    fun add(@Valid @RequestBody req: MovieRequest)
            = ResponseEntity.ok().body(movieService.add(req))

}
