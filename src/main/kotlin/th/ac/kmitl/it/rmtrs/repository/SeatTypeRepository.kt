package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import th.ac.kmitl.it.rmtrs.model.SeatType
import javax.transaction.Transactional

interface SeatTypeRepository: JpaRepository<SeatType, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE SeatType SET isActive = false WHERE id = :id")
    fun softDelete(@Param("id") id: Long)
}
