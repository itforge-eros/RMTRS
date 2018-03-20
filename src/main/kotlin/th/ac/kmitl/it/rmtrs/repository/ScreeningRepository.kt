package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import th.ac.kmitl.it.rmtrs.model.Screening
import java.time.LocalDate
import javax.transaction.Transactional

@Repository
interface ScreeningRepository: JpaRepository<Screening, Long> {

    @Query("SELECT sc FROM Screening sc WHERE sc.movie.id IN :movieIds AND sc.showDate = :date AND sc.isActive = 1")
    fun findByMovieIdsInAndShowDate(
            @Param("movieIds")movieIds: List<Long>,
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

}
