package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import th.ac.kmitl.it.rmtrs.model.Movie
import th.ac.kmitl.it.rmtrs.model.Theatre
import javax.transaction.Transactional

@Repository
interface TheatreRepository: JpaRepository<Theatre, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Theatre SET isActive = false WHERE id = :id")
    fun softDelete(@Param("id") id: Long)

    @Query("SELECT t FROM Theatre t WHERE t.isActive = 1")
    override fun findAll(pageable: Pageable): Page<Theatre>
}
