package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.repository.SeatRepository

@Service
class SeatService(val seatRepository: SeatRepository) {

    fun checkIfExisted(id: Long)
            = seatRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Seat id: ${id} not found.") }
}