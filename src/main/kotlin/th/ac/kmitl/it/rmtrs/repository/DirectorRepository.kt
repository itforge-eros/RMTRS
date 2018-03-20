package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import th.ac.kmitl.it.rmtrs.model.Actor
import th.ac.kmitl.it.rmtrs.model.Director
import java.util.*
import javax.transaction.Transactional

interface DirectorRepository: JpaRepository<Director, Long> {
    fun findById(id: String): Optional<Director>

    @Transactional
    @Modifying
    @Query("UPDATE Director SET isActive = false WHERE id = :id")
    fun softDelete(@Param("id") id: Long)
}