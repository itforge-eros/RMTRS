package th.ac.kmitl.it.rmtrs.payload

import javax.validation.constraints.NotBlank

data class TheatreRequest(
        @field:NotBlank
        val name: String = "default",

        var seats: List<SeatRequest>
)