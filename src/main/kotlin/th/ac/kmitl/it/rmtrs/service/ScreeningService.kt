package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.exception.ScreeningTimeConflict
import th.ac.kmitl.it.rmtrs.model.Movie
import th.ac.kmitl.it.rmtrs.model.Theatre
import th.ac.kmitl.it.rmtrs.payload.ScreeningRequest
import th.ac.kmitl.it.rmtrs.repository.MovieRepository
import th.ac.kmitl.it.rmtrs.repository.ScreeningRepository
import th.ac.kmitl.it.rmtrs.util.isOverlap
import th.ac.kmitl.it.rmtrs.util.toModel
import th.ac.kmitl.it.rmtrs.util.toResponse
import th.ac.kmitl.it.rmtrs.util.toScreeningWithDetail
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class ScreeningService(
        val screeningRepository: ScreeningRepository,
        val movieRepository: MovieRepository
) {

    @PersistenceContext
    lateinit var em: EntityManager

    val modelName = "Screening"

    fun getScreeningsByMovieIdsAndDate(movieIds: List<Long>, date: LocalDate)
            = screeningRepository
            .findByMovieIdsInAndShowDate(movieIds, date)
            .filter { it.showDate.isEqual(date) }
            .map { it.toScreeningWithDetail() }

    fun get(id: Long)
            = screeningRepository
            .findById(id)
            .map { it.toScreeningWithDetail() }
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun add(req: ScreeningRequest): Map<String, Any> {
        val movie = validateScreeningConflict(req)
        val screening = req.toModel()
                .apply {
                    this.movie = movie
                    this.theatre = em.getReference(Theatre::class.java, req.theatreId)
                }
        return screeningRepository.save(screening).toScreeningWithDetail()
    }

    fun update(req: ScreeningRequest, id: Long): Map<String, Any> {
        validateScreeningConflict(req)
        val screening = screeningRepository
                .findById(id)
                .map {
                    req.toModel()
                        .apply { this.id = it.id }
                        .let {
                            it.movie = em.getReference(Movie::class.java, req.movieId)
                            it.theatre = em.getReference(Theatre::class.java, req.theatreId)
                            screeningRepository.save(it)
                        } }
                .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }
        return screening.toScreeningWithDetail()
    }

    fun delete(id: Long)
            = screeningRepository.findById(id)
            .map { screeningRepository.softDelete(it.id) }
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    private fun validateScreeningConflict(req: ScreeningRequest): Movie {
        val movie = movieRepository.findById(req.movieId).orElseThrow { ResourceNotFoundException("Movie id: ${req.movieId} not found.") }
        val allScreeningsInTheatre = screeningRepository.findAllByTheatreId(req.theatreId, req.showDate)
        allScreeningsInTheatre
                .sortedBy { it.showTime }
                .forEach { if(isOverlap(
                                it.showTime,
                                req.showTime,
                                it.showTime.plusMinutes(movie.duration.toLong()),
                                req.showTime.plusMinutes(movie.duration.toLong())))
                    throw ScreeningTimeConflict("Screening time conflict with ${it.id}", it)
                }
        return movie
    }
}