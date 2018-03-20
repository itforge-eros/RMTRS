package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import th.ac.kmitl.it.rmtrs.model.Actor
import th.ac.kmitl.it.rmtrs.model.Director
import th.ac.kmitl.it.rmtrs.model.Genre
import java.util.*
import javax.transaction.Transactional

interface GenreRepository: JpaRepository<Genre, Long> {
    fun findById(id: String): Optional<Genre>

    @Transactional
    @Modifying
    @Query("UPDATE Genre SET isActive = false WHERE id = :id")
    fun softDelete(@Param("id") id: Long)
}