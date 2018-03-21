package th.ac.kmitl.it.rmtrs.payload

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class ReservationRequest(
        @JsonProperty("screening_id")
        @field:NotNull
        var screeningId: Long,

        @field:NotNull
        var tickets: List<TicketRequest>
)