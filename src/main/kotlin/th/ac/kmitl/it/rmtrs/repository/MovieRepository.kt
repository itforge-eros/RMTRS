package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import th.ac.kmitl.it.rmtrs.model.Movie
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Repository
interface MovieRepository: JpaRepository<Movie, Long> {

    override fun findById(movieId: Long): Optional<Movie>

    @Query("SELECT m FROM Movie m WHERE :date BETWEEN m.releaseDate AND m.endDate AND m.isActive = 1")
    fun findAvailableMoviesInThatDate(@Param("date") date: LocalDate): List<Movie>

    @Query("SELECT m FROM Movie m WHERE m.id = :id AND:date BETWEEN m.releaseDate AND m.endDate AND m.isActive = 1")
    fun findMovieByMoviesInThatDate(@Param("id") id: Long, @Param("date") date: LocalDate): Optional<Movie>
}
