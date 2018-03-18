package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import th.ac.kmitl.it.rmtrs.model.Actor
import java.util.*

interface ActorRepository: JpaRepository<Actor, Long> {
    fun findById(id: String): Optional<Actor>
}