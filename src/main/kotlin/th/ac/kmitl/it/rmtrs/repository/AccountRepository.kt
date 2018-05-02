package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import th.ac.kmitl.it.rmtrs.model.Account
import th.ac.kmitl.it.rmtrs.model.Theatre
import java.util.*
import javax.transaction.Transactional

interface AccountRepository: JpaRepository<Account, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Account SET isActive = false WHERE id = :id")
    fun softDelete(@Param("id") id: Long)

    fun findByUsername(username: String): Optional<Account>

    fun findByUsernameAndPassword(username: String, password: String): Optional<Account>

    @Query("SELECT a FROM Account a WHERE a.isActive = 1")
    override fun findAll(pageable: Pageable): Page<Account>
}