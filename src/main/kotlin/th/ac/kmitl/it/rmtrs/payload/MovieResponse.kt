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
        var releaseDate: String,
        var endDate: String,
        var rate: Rate = Rate.G,
        var createAt: String,
        var updateAt: String,
        var isActive: Boolean
)
