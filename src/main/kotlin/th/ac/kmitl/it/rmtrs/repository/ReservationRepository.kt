package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import th.ac.kmitl.it.rmtrs.model.Reservation

@Repository
interface ReservationRepository: JpaRepository<Reservation, Long> {


}