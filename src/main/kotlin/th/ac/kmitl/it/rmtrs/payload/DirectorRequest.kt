package th.ac.kmitl.it.rmtrs.payload

import javax.validation.constraints.NotBlank

data class DirectorRequest(
        var id: Long = 0,
        @field:NotBlank var fname: String = "default",
        @field:NotBlank var mname: String = "default",
        @field:NotBlank var lname: String = "default"
)