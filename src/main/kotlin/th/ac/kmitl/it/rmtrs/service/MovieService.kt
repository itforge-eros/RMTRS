package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.model.*
import th.ac.kmitl.it.rmtrs.payload.*
import th.ac.kmitl.it.rmtrs.repository.MovieRepository
import th.ac.kmitl.it.rmtrs.util.toModel
import th.ac.kmitl.it.rmtrs.util.toMovieWithDetail
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class MovieService(val movieRepository: MovieRepository) {

    @PersistenceContext
    lateinit var em: EntityManager

    val modelName = "Movie"

    fun getAllAvailableMoviesWithScreeningAmount(date: LocalDate): List<Map<String, Any>> {
        val movies = movieRepository
                .findAvailableMoviesInThatDate(date)
        if (movies.count() == 0) return emptyList<Nothing>()
        movies.forEach { it.screenings.removeIf { !it.isActive } } // Filter only that date's screening
        println("Available Movies and Showtime: $movies")
        return movies.map { it.toMovieWithDetail().plus("screening_amount" to it.screenings.count()) }
    }

    fun getByDate(id: Long, date: LocalDate): Map<String, Any>
            = movieRepository.findMovieByMoviesInThatDate(id, date)
            .map { it.toMovieWithDetail()
                    .plus("screenings" to it.screenings.removeIf { !it.isActive })
            }.orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun add(req: MovieRequest)
            = movieRepository.save(req.toModel().apply {
                req.actors.forEach { id -> this.actors.add(em.getReference(Actor::class.java, id)) }
                req.directors.forEach { id -> this.directors.add(em.getReference(Director::class.java, id)) }
                req.genres.forEach { id -> this.genres.add(em.getReference(Genre::class.java, id)) }
                req.productions.forEach { id -> this.productions.add(em.getReference(Production::class.java, id)) }
            }).toMovieWithDetail()

    fun update(req: MovieRequest, id: Long) {
        val movie = checkIfExisted(id)
        return req.toModel()
                .apply { this.id = movie.id }
                .let { movieRepository.save(it).toMovieWithDetail() }
    }

    fun delete(id: Long)
            = movieRepository.findById(id)
            .map { movieRepository.softDelete(it.id) }
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun checkIfExisted(id: Long)
            = movieRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("Movie id: ${id} not found.") }
}
