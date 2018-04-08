package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.exception.ScreeningTimeConflict
import th.ac.kmitl.it.rmtrs.model.Movie
import th.ac.kmitl.it.rmtrs.model.Theatre
import th.ac.kmitl.it.rmtrs.payload.ScreeningRequest
import th.ac.kmitl.it.rmtrs.repository.ScreeningRepository
import th.ac.kmitl.it.rmtrs.util.*
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class ScreeningService(
        val screeningRepository: ScreeningRepository,
        val movieService: MovieService,
        val firebaseService: FirebaseService
) {

    @PersistenceContext
    lateinit var em: EntityManager

    val modelName = "Screening"

    fun getScreeningsByMovieIdsAndDate(movieId: Long, date: LocalDate)
            = screeningRepository
            .findByMovieIdAndShowDate(movieId, date)
            .groupBy { it.theatre }
            .map { (theatre, screenings) -> mapOf("theatre" to theatre.withoutSeats(), "screenings" to screenings) }
    fun get(id: Long)
            = checkIfExisted(id).toScreeningWithDetail()

    fun add(req: ScreeningRequest): Map<String, Any> {
        val movie = validateScreeningConflict(req)
        val screening = req.toModel()
                .apply {
                    this.movie = movie
                    this.theatre = em.getReference(Theatre::class.java, req.theatreId)
                }
        return screeningRepository.save(screening)
                .also { firebaseService.createScreening(screeningId = it.id) }
                .toScreeningWithDetail()
    }

    fun update(req: ScreeningRequest, id: Long): Map<String, Any> {
        val movie = validateScreeningConflict(req)
        val screening = checkIfExisted(id)
        req.toModel()
                .apply { this.id = screening.id }
                .let {
                    it.movie = movie
                    it.theatre = em.getReference(Theatre::class.java, req.theatreId)
                    screeningRepository.save(it)
                }
        return screening.toScreeningWithDetail()
    }

    fun delete(id: Long)
            = checkIfExisted(id).let { screeningRepository.softDelete(id) }

    private fun validateScreeningConflict(req: ScreeningRequest): Movie {
        val movie = movieService.checkIfExisted(req.movieId)
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

    fun checkIfExisted(id: Long)
            = screeningRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }
}