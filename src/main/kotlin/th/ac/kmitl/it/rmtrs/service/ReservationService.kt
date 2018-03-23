package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.model.Reservation
import th.ac.kmitl.it.rmtrs.model.Screening
import th.ac.kmitl.it.rmtrs.model.Ticket
import th.ac.kmitl.it.rmtrs.payload.ReservationRequest
import th.ac.kmitl.it.rmtrs.repository.ReservationRepository
import th.ac.kmitl.it.rmtrs.util.toReservationWithDetail
import java.time.LocalDateTime
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class ReservationService(
        val reservationRepository: ReservationRepository,
        val screeningService: ScreeningService,
        val seatService: SeatService,
        val firebaseService: FirebaseService
) {

    val modelName = "Reservation"

    fun reserve(req: ReservationRequest): Map<String, Any> {
        val screening = screeningService.checkIfExisted(req.screeningId)
        val reservation = Reservation().apply { this.screening = screening }
        val tickets = req.tickets.map {
            val ticket = Ticket(itemNo = it.itemNo)
            ticket.reservation = reservation
            ticket.seat = seatService.checkIfExisted(it.seatId)
            firebaseService.reserveSeat(screeningId = screening.id, seatId = ticket.seat.id)
            ticket
        }

        reservation.tickets.addAll(tickets)
        return reservationRepository.save(reservation).toReservationWithDetail()
    }

    fun checkIfExisted(id: Long)
            = reservationRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun checkIn(id: Long): Map<String, Any> {
        val reservation = checkIfExisted(id)
        reservation.isCheckedIn = true
        reservation.checkedInTime = LocalDateTime.now()
        return reservationRepository.save(reservation).toReservationWithDetail()
    }
}