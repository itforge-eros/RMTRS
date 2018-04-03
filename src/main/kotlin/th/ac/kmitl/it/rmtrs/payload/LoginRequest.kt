package th.ac.kmitl.it.rmtrs.payload

import javax.validation.constraints.NotBlank

data class LoginRequest(

        @field:NotBlank
        var username: String,
        @field:NotBlank
        var password: String
)