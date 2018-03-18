package th.ac.kmitl.it.rmtrs.payload

import th.ac.kmitl.it.rmtrs.definition.Rate
import th.ac.kmitl.it.rmtrs.model.*
import java.time.LocalDate

data class MovieResponse(
        var id: Long = 0,
        var th_title: String = "default",
        var en_title: String = "default",
        var synopsis: String = "default",
        var duration: Int = 0,
        var posterUrl: String = "default",
        var trailerUrl: String = "default",
        var releaseDate: LocalDate = LocalDate.now(),
        var endDate: LocalDate = LocalDate.now(),
        var rate: Rate = Rate.G,
        var actors: List<ActorResponse>,
        var genres: List<GenreResponse>,
        var productions: List<ProductionResponse>,
        var directors: List<DirectorResponse>
)
