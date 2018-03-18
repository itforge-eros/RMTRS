package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import th.ac.kmitl.it.rmtrs.payload.ActorRequest
import th.ac.kmitl.it.rmtrs.payload.ActorResponse
import th.ac.kmitl.it.rmtrs.service.ActorService
import javax.validation.Valid

@RestController
@RequestMapping("/actor")
class ActorController(val service: ActorService) {

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long)
            = ResponseEntity.ok(service.get(id))

    @PostMapping
    fun add(@Valid @RequestBody req: ActorRequest): ResponseEntity<ActorResponse> {
        val actor = service.add(req)
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(actor.id).toUri()).body(actor)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody req: ActorRequest)
            = ResponseEntity.ok(service.update(req, id))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity(HttpStatus.OK)
    }
}