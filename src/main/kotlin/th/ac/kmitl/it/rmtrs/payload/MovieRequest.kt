package th.ac.kmitl.it.rmtrs.payload

import th.ac.kmitl.it.rmtrs.definition.Rate
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.PositiveOrZero

data class MovieRequest(
        var id: Long = 0,
        @field:NotBlank var th_title: String = "default",
        @field:NotBlank var en_title: String = "default",
        @field:NotBlank var synopsis: String = "default",
        @field:PositiveOrZero var duration: Int = 0,
        @field:NotBlank var posterUrl: String = "default",
        @field:NotBlank var trailerUrl: String = "default",
        @field:NotNull var releaseDate: LocalDate = LocalDate.now(),
        @field:NotNull var endDate: LocalDate = LocalDate.now(),
        @field:NotNull var rate: Rate = Rate.G,
        var actors: List<Long>,
        var genres: List<Long>,
        var productions: List<Long>,
        var directors: List<Long>
)