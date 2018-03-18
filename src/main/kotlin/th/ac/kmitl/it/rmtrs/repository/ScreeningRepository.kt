package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import th.ac.kmitl.it.rmtrs.model.Screening
import java.time.LocalDate

@Repository
interface ScreeningRepository: JpaRepository<Screening, Long> {

    fun findByIdInAndShowDate(screeningIds: List<Long>, date: LocalDate): List<Screening>
}
