package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.util.toResponse
import th.ac.kmitl.it.rmtrs.payload.ActorRequest
import th.ac.kmitl.it.rmtrs.repository.ActorRepository
import th.ac.kmitl.it.rmtrs.util.toModel

@Service
class ActorService(val repository: ActorRepository) {

    val modelName = "Actor"

    fun getAll() = repository.findAll().map { it.toResponse() }

    fun get(id: Long)
            = repository.findById(id)
            .map { it.toResponse() }
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun add(req: ActorRequest) = repository.save(req.toModel()).toResponse()

    fun update(req: ActorRequest, id: Long)
            = repository.findById(id)
            .map {
                req.toModel()
                        .apply { this.id = it.id }
                        .let {
                            repository.save(it)
                            it.toResponse()
                        }
            }
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

    fun delete(id: Long)
            = repository.findById(id)
            .map { repository.delete(it) }
            .orElseThrow { ResourceNotFoundException("$modelName id: $id not found.") }

}