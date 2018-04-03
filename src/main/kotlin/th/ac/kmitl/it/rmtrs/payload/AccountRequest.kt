package th.ac.kmitl.it.rmtrs.payload

import javax.validation.constraints.NotBlank

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
        var phone: String = "default"
)