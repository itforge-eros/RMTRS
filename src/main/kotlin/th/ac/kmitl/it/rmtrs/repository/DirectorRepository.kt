package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import th.ac.kmitl.it.rmtrs.model.Actor
import th.ac.kmitl.it.rmtrs.model.Director
import java.util.*

interface DirectorRepository: JpaRepository<Director, Long> {
    fun findById(id: String): Optional<Director>
}