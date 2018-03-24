package th.ac.kmitl.it.rmtrs.payload

import javax.validation.constraints.NotBlank
import javax.validation.constraints.PositiveOrZero

data class SeatTypeRequest(
        @field:NotBlank
        val name: String,
        @field:PositiveOrZero
        val price: Double,
        @field:NotBlank
        val description: String
)