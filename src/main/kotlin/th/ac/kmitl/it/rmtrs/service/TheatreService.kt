package th.ac.kmitl.it.rmtrs.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.model.Movie
import th.ac.kmitl.it.rmtrs.model.Theatre
import th.ac.kmitl.it.rmtrs.payload.PagedResponse
import th.ac.kmitl.it.rmtrs.payload.TheatreRequest
import th.ac.kmitl.it.rmtrs.repository.TheatreRepository
import th.ac.kmitl.it.rmtrs.util.toPagedResponse
import th.ac.kmitl.it.rmtrs.util.toTheatreWithDetail

@Service
class TheatreService(val theatreRepository: TheatreRepository) {

    val modelName = "Theatre"

    fun get(id: Long) = checkIfExisted(id).toTheatreWithDetail()

    fun add(req: TheatreRequest): Map<String, Any> {
        val theatre = Theatre(name = req.name)
        return theatreRepository.save(theatre).toTheatreWithDetail()
    }

    fun update(id: Long, req: TheatreRequest): Map<String, Any> {
        val theatre = checkIfExisted(id)
        theatre.name = req.name
        return theatreRepository.save(theatre).toTheatreWithDetail()
    }

    fun delete(id: Long) {
        checkIfExisted(id)
        theatreRepository.softDelete(id)
    }

    fun checkIfExisted(id: Long)
            = theatreRepository.findById(id)
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun getPaged(page: Int, size: Int): PagedResponse<Map<String, Any>> {
        val pagedRes = PageRequest.of(page, size, Sort.Direction.DESC, "createAt")
                .let { theatreRepository.findAll(it) }
        return toPagedResponse(pagedRes, pagedRes.content.map { it.toTheatreWithDetail() })
    }
}