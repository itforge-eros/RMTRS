package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.model.*
import th.ac.kmitl.it.rmtrs.payload.*
import th.ac.kmitl.it.rmtrs.util.toResponse
import th.ac.kmitl.it.rmtrs.repository.MovieRepository
import th.ac.kmitl.it.rmtrs.repository.ScreeningRepository
import th.ac.kmitl.it.rmtrs.util.JSON
import th.ac.kmitl.it.rmtrs.util.toModel
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class MovieService(
        val movieRepository: MovieRepository,
        val screeningRepository: ScreeningRepository
) {

    @PersistenceContext
    lateinit var em: EntityManager

    val modelName = "Movie"

    fun getAllAvailableMoviesWithScreeningAmount(date: LocalDate): Map<String, Any> {
        val availableMovies = movieRepository.findAvailableMoviesBetweenEndDateAndReleaseDate(date)
        println("Available Movies: $availableMovies")
        val dateProp = "date" to date
        if (availableMovies.count() == 0) return mapOf(dateProp, "movies" to emptyList<Nothing>())
        val screeningsMap = screeningRepository //Available Screenings Map: {MovieID=[Screening...]}
                .findByIdInAndShowDate(availableMovies.map { it.id }, date)
                .groupBy { it.movie.id }
        println("Available Screenings Map: $screeningsMap")
        return availableMovies
                .map {
                    toMovieWithDetail(it).plus("screening_amount" to (screeningsMap[it.id]?.count() ?: 0))
                }.let { mapOf(dateProp, "movies" to it) }
    }

    fun get(id: Long)
            = movieRepository.findById(id)
            .map { toMovieWithDetail(it) }
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun add(req: MovieRequest)
            = movieRepository.save(req.toModel().apply {
        req.actors.forEach { id -> this.actors.add(em.getReference(Actor::class.java, id)) }
        req.directors.forEach { id -> this.directors.add(em.getReference(Director::class.java, id)) }
        req.genres.forEach { id -> this.genres.add(em.getReference(Genre::class.java, id)) }
        req.productions.forEach { id -> this.productions.add(em.getReference(Production::class.java, id)) }
    }).let { toMovieWithDetail(it) }

    fun update(req: MovieRequest, id: Long)
            = movieRepository.findById(id)
            .map {
                req.toModel()
                        .apply { this.id = it.id }
                        .let {
                            toMovieWithDetail(movieRepository.save(it))
                        }
            }.orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun delete(id: Long)
            = movieRepository.findById(id)
            .map { movieRepository.delete(it) }
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    private fun toMovieWithDetail(movie: Movie)
            = JSON.toResponseMap(movie).plus(mapOf(
            "actors" to movie.actors,
            "directors" to movie.directors,
            "genres" to movie.genres,
            "productions" to movie.productions
    ))
}
