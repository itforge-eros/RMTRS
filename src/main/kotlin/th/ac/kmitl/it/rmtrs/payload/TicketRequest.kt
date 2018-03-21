package th.ac.kmitl.it.rmtrs.payload

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class TicketRequest(
        @field:NotNull
        @JsonProperty("seat_id")
        var seatId: Long,
        @field:NotNull
        @JsonProperty("item_no")
        var itemNo: Int
)