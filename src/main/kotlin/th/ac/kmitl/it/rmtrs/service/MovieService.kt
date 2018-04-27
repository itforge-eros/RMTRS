package th.ac.kmitl.it.rmtrs.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.UriComponentsBuilder
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.model.*
import th.ac.kmitl.it.rmtrs.payload.*
import th.ac.kmitl.it.rmtrs.repository.MovieRepository
import th.ac.kmitl.it.rmtrs.util.toModel
import th.ac.kmitl.it.rmtrs.util.toMovieWithDetail
import th.ac.kmitl.it.rmtrs.util.toPagedResponse
import java.time.LocalDate
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class MovieService(
        val movieRepository: MovieRepository,
        val genreService: GenreService,
        val actorService: ActorService,
        val productionService: ProductionService,
        val directorService: DirectorService
) {

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
            .map { it.toMovieWithDetail() }
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun add(req: MovieRequest)
            = movieRepository.save(req.toModel().apply {
                req.actors.forEach { id -> this.actors.add(actorService.checkIfExisted(id)) }
                req.directors.forEach { id -> this.directors.add(directorService.checkIfExisted(id)) }
                req.genres.forEach { id -> this.genres.add(genreService.checkIfExisted(id)) }
                req.productions.forEach { id -> this.productions.add(productionService.checkIfExisted(id)) }
            }).toMovieWithDetail()

    fun update(req: MovieRequest, id: Long): Map<String, Any> {
        val movie = checkIfExisted(id)
        movie.actors.addAll(req.actors.map { movieId -> actorService.checkIfExisted(movieId) })
        movie.directors.addAll(req.directors.map { dId -> directorService.checkIfExisted(dId) })
        movie.genres.addAll(req.genres.map { gId -> genreService.checkIfExisted(gId) })
        movie.productions.addAll(req.productions.map { pId -> productionService.checkIfExisted(pId) })
        movie.th_title = req.th_title
        movie.en_title = req.en_title
        movie.synopsis = req.synopsis
        movie.duration = req.duration
        movie.posterUrl = req.posterUrl
        movie.trailerUrl = req.trailerUrl
        movie.releaseDate = req.releaseDate
        movie.endDate = req.endDate
        movie.rate = req.rate
        return movieRepository.save(movie).toMovieWithDetail()
    }

    fun delete(id: Long)
            = movieRepository.findById(id)
            .map { movieRepository.softDelete(it.id) }
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun checkIfExisted(id: Long)
            = movieRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("Movie id: ${id} not found.") }

    fun getPaged(page: Int, size: Int): PagedResponse<Movie> {
        val pagedRes = PageRequest.of(page, size, Sort.Direction.DESC, "createAt")
                .let { movieRepository.findAll(it) }
        return toPagedResponse(pagedRes, pagedRes.content)
    }
}
