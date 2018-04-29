package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.model.Seat
import th.ac.kmitl.it.rmtrs.payload.SeatRequest
import th.ac.kmitl.it.rmtrs.repository.SeatRepository
import th.ac.kmitl.it.rmtrs.util.toSeatWithDetail

@Service
class SeatService(val seatRepository: SeatRepository, val seatTypeService: SeatTypeService, val theatreService: TheatreService) {

    val modelName = "Seat Type"

    fun get(id: Long) = checkIfExisted(id).toSeatWithDetail()

    fun add(req: SeatRequest): Map<String, Any> {
        val seat = Seat(row = req.row, number = req.number)
        seat.seatType = seatTypeService.checkIfExisted(req.seatTypeId)
        return seatRepository.save(seat).toSeatWithDetail()
    }

    fun update(id: Long, req: SeatRequest): Map<String, Any> {
        val seat = checkIfExisted(id)
        val newSeat = seat.copy(row = req.row, number = req.number)
        newSeat.theatre = seat.theatre
        newSeat.seatType = seatTypeService.checkIfExisted(req.seatTypeId)
        return seatRepository.save(newSeat).toSeatWithDetail()
    }

    fun delete(id: Long) {
        checkIfExisted(id)
        seatRepository.softDelete(id)
    }


    fun checkIfExisted(id: Long)
            = seatRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("Seat id: ${id} not found.") }
}