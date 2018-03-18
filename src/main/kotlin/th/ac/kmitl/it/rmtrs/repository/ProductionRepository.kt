package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import th.ac.kmitl.it.rmtrs.model.Actor
import th.ac.kmitl.it.rmtrs.model.Production
import java.util.*

interface ProductionRepository: JpaRepository<Production, Long> {
    fun findById(id: String): Optional<Production>
}