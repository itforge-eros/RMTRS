package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import th.ac.kmitl.it.rmtrs.model.Seat
import javax.transaction.Transactional

interface SeatRepository: JpaRepository<Seat, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Seat SET isActive = false WHERE id = :id")
    fun softDelete(@Param("id") id: Long)
}
