package th.ac.kmitl.it.rmtrs.payload

import th.ac.kmitl.it.rmtrs.definition.Role
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class AccountRequest(

        @field:NotBlank
        var username: String = "default",

        var password: String = "default",

        @field:NotBlank
        var title: String = "default",

        @field:NotBlank
        var fname: String = "default",

        @field:NotBlank
        var lname: String = "default",

        @field:NotBlank
        var phone: String = "default",

        @field:NotNull
        var role: Role
)