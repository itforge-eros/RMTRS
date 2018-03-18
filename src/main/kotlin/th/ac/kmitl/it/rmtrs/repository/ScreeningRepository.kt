package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import th.ac.kmitl.it.rmtrs.model.Screening
import java.time.LocalDate

@Repository
interface ScreeningRepository: JpaRepository<Screening, Long> {

    @Query("SELECT sc FROM Screening sc WHERE sc.movie.id IN :movieIds AND sc.showDate = :date AND sc.isActive = 1")
    fun findByIdInAndShowDate(
            @Param("movieIds")movieIds: List<Long>,
            @Param("date")date: LocalDate
    ): List<Screening>
}
