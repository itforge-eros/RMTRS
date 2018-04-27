package th.ac.kmitl.it.rmtrs.payload

import javax.validation.constraints.NotBlank

data class ActorRequest(
        var id: Long = 0,
        @field:NotBlank var fname: String = "default",
        var mname: String = "default",
        @field:NotBlank var lname: String = "default"
)