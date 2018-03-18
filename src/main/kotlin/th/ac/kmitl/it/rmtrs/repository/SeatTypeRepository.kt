package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import th.ac.kmitl.it.rmtrs.model.SeatType

interface SeatTypeRepository: JpaRepository<SeatType, Long> {
}
