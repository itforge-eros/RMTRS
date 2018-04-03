package th.ac.kmitl.it.rmtrs.service

import org.springframework.stereotype.Service
import th.ac.kmitl.it.rmtrs.exception.ResourceNotFoundException
import th.ac.kmitl.it.rmtrs.exception.UsernameConflict
import th.ac.kmitl.it.rmtrs.model.Account
import th.ac.kmitl.it.rmtrs.payload.AccountRequest
import th.ac.kmitl.it.rmtrs.payload.LoginRequest
import th.ac.kmitl.it.rmtrs.repository.AccountRepository
import th.ac.kmitl.it.rmtrs.util.sha1
import th.ac.kmitl.it.rmtrs.util.toModel

@Service
class AccountService(val accountRepository: AccountRepository) {

    fun register(req: AccountRequest): Account {
        accountRepository.findByUsername(req.username)
                .ifPresent { throw UsernameConflict("This username has been used.") }
        return accountRepository.save(req.toModel()).apply { password = "---------" }
    }

    fun login(req: LoginRequest): Account {
        return accountRepository.findByUsernameAndPassword(req.username, req.password.sha1())
                .orElseThrow { ResourceNotFoundException("Username or Password is incorrect or not found.") }
                .apply { password = "---------" }
    }

    fun checkIfExisted(id: Long)
            = accountRepository
            .findById(id)
            .orElseThrow { ResourceNotFoundException("Account not found.") }

    fun update(req: AccountRequest, id: Long): Account {
        val account = checkIfExisted(id)
        val newAccount = req.toModel()
        if (req.password.isBlank()) {
            newAccount.password = account.password
        }
        newAccount.id = account.id
        return accountRepository.save(newAccount).apply { password = "---------" }
    }

    fun delete(id: Long)
            = accountRepository.findById(id)
            .map { accountRepository.softDelete(it.id) }
            .orElseThrow { ResourceNotFoundException("Account not found.") }
}