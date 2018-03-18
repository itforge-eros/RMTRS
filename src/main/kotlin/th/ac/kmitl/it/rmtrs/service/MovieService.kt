package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.util.toResponse
import th.ac.kmitl.it.rmtrs.payload.AvailableMoviesByDate
import th.ac.kmitl.it.rmtrs.payload.EntityWithAmountOf
import th.ac.kmitl.it.rmtrs.repository.MovieRepository
import th.ac.kmitl.it.rmtrs.repository.ScreeningRepository
import java.time.LocalDate

@Service
class MovieService(
        val movieRepository: MovieRepository,
        val screeningRepository: ScreeningRepository
) {

    fun getAllAvailableMoviesWithScreeningAmount(date: LocalDate): AvailableMoviesByDate {
        val availableMovies = movieRepository.findAvailableMoviesBetweenEndDateAndReleaseDate(date)
        println("Available Movies: $availableMovies")
        val screeningsMap = screeningRepository //Available Screenings Map: {MovieID=[Screening...]}
                .findByIdInAndShowDate(availableMovies.map { it.id }, date)
                .groupBy { it.movie.id }
        println("Available Screenings Map: $screeningsMap")
        return availableMovies
                .map { it.toResponse() }
                .map { EntityWithAmountOf(it, mapOf("screening" to (screeningsMap.get(it.id)?.count() ?: -1))) }
                .let { AvailableMoviesByDate(date, it) }
    }
}
