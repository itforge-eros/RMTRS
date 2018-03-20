package th.ac.kmitl.it.rmtrs.payload

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.NotNull

data class ScreeningRequest(

        @JsonProperty("movie_id")
        @field:NotNull
        val movieId: Long = 0,

        @JsonProperty("theatre_id")
        @field:NotNull
        val theatreId: Long = 0,

        @JsonProperty("show_date")
        @field:NotNull
        @field:FutureOrPresent
        val showDate: LocalDate,

        @JsonProperty("show_time")
        @field:NotNull
        @field:FutureOrPresent
        @JsonDeserialize(using = LocalDateTimeDeserializer::class)
        val showTime: LocalDateTime
)