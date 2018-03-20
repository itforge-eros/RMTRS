package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import th.ac.kmitl.it.rmtrs.model.Actor
import java.util.*
import javax.transaction.Transactional

interface ActorRepository: JpaRepository<Actor, Long> {
    fun findById(id: String): Optional<Actor>

    @Transactional
    @Modifying
    @Query("UPDATE Actor SET isActive = false WHERE id = :id")
    fun softDelete(@Param("id") id: Long)
}