package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import th.ac.kmitl.it.rmtrs.model.Movie
import th.ac.kmitl.it.rmtrs.model.Reservation

@Repository
interface ReservationRepository: JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.isActive = 1")
    override fun findAll(pageable: Pageable): Page<Reservation>
}