package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import th.ac.kmitl.it.rmtrs.payload.AvailableMoviesByDate
import th.ac.kmitl.it.rmtrs.payload.MovieResponse
import th.ac.kmitl.it.rmtrs.service.MovieService
import th.ac.kmitl.it.rmtrs.util.toLocalDate
import java.time.LocalDate

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

}
