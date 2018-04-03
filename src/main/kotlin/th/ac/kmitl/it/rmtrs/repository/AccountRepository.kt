package th.ac.kmitl.it.rmtrs.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import th.ac.kmitl.it.rmtrs.model.Account
import java.util.*
import javax.transaction.Transactional

interface AccountRepository: JpaRepository<Account, Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Account SET isActive = false WHERE id = :id")
    fun softDelete(@Param("id") id: Long)

    fun findByUsername(username: String): Optional<Account>

    fun findByUsernameAndPassword(username: String, password: String): Optional<Account>
}