package th.ac.kmitl.it.rmtrs.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import th.ac.kmitl.it.rmtrs.model.Account
import th.ac.kmitl.it.rmtrs.payload.AccountRequest
import th.ac.kmitl.it.rmtrs.payload.LoginRequest
import th.ac.kmitl.it.rmtrs.payload.PagedResponse
import th.ac.kmitl.it.rmtrs.service.AccountService
import javax.validation.Valid

@RestController
@RequestMapping("/account")
class AccountController(val accountService: AccountService) {

    @PostMapping("/login")
    fun login(@Valid @RequestBody req: LoginRequest): ResponseEntity<Account> {
        return ResponseEntity.ok(accountService.login(req))
    }

    @PostMapping("/register")
    fun register(@Valid @RequestBody req: AccountRequest): ResponseEntity<Account> {
        return ResponseEntity.ok(accountService.register(req))
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody req: AccountRequest, @PathVariable("id") id: Long): ResponseEntity<Account> {
        return ResponseEntity.ok(accountService.update(req, id))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {

    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long)
            = ResponseEntity.ok(accountService.checkIfExisted(id).apply { password = "--------" })

    @GetMapping("paged")
    fun getPaged(@RequestParam(value = "page", defaultValue = "0") page: Int): ResponseEntity<PagedResponse<Account>> {
        return ResponseEntity.ok(accountService.getPaged(page, PagedResponse.PAGE_SIZE))
    }
}
