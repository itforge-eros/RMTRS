package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import th.ac.kmitl.it.rmtrs.model.Movie
import th.ac.kmitl.it.rmtrs.model.Screening
import java.time.LocalDate
import javax.transaction.Transactional

@Repository
interface ScreeningRepository: JpaRepository<Screening, Long> {

    @Query("SELECT sc FROM Screening sc WHERE sc.movie.id = :movieId AND sc.showDate = :date AND sc.isActive = 1")
    fun findByMovieIdAndShowDate(
            @Param("movieId")movieId: Long,
            @Param("date")date: LocalDate
    ): List<Screening>

    @Query("SELECT sc FROM Screening sc WHERE sc.theatre.id = :theatreId " +
            "AND sc.showDate = :date AND sc.isActive = 1")
    fun findAllByTheatreId(
            @Param("theatreId") theatreId: Long,
            @Param("date")date: LocalDate
    ): List<Screening>

    @Transactional
    @Modifying
    @Query("UPDATE Screening SET isActive = false WHERE id = :id")
    fun softDelete(@Param("id") id: Long)

    @Query("SELECT s FROM Screening s WHERE s.isActive = 1")
    override fun findAll(pageable: Pageable): Page<Screening>

}
