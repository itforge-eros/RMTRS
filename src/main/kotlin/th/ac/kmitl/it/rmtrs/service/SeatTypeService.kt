package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.model.SeatType
import th.ac.kmitl.it.rmtrs.payload.SeatTypeRequest
import th.ac.kmitl.it.rmtrs.repository.SeatTypeRepository
import th.ac.kmitl.it.rmtrs.util.toSeatTypeWithDetail

@Service
class SeatTypeService(val seatTypeRepository: SeatTypeRepository) {

    val modelName = "Seat Type"

    fun getAll() = seatTypeRepository.findAll().map { it.toSeatTypeWithDetail() }

    fun get(id: Long) = checkIfExisted(id).toSeatTypeWithDetail()

    fun add(req: SeatTypeRequest): Map<String, Any> {
        val seatType = SeatType(name = req.name, price = req.price, description = req.description)
        return seatTypeRepository.save(seatType).toSeatTypeWithDetail()
    }

    fun update(id: Long, req: SeatTypeRequest): Map<String, Any> {
        val seatType = checkIfExisted(id)
        val newSeatType = seatType.copy(name = req.name, price = req.price, description = req.description)
        return seatTypeRepository.save(newSeatType).toSeatTypeWithDetail()
    }

    fun delete(id: Long) {
        checkIfExisted(id)
        seatTypeRepository.softDelete(id)
    }

    fun checkIfExisted(id: Long)
            = seatTypeRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }
}