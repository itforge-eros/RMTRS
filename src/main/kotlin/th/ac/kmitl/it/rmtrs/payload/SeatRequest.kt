package th.ac.kmitl.it.rmtrs.payload

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class SeatRequest(
        @field:NotNull
        @JsonProperty("seat_type_id")
        val seatTypeId: Long,
        @field:NotBlank
        val row: String,
        @field:Positive
        val number: Int
)