package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import th.ac.kmitl.it.rmtrs.model.Actor
import th.ac.kmitl.it.rmtrs.model.Director
import th.ac.kmitl.it.rmtrs.model.Genre
import java.util.*

interface GenreRepository: JpaRepository<Genre, Long> {
    fun findById(id: String): Optional<Genre>
}