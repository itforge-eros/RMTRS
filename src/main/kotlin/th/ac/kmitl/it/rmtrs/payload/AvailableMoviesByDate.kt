package th.ac.kmitl.it.rmtrs.payload

import java.time.LocalDate

data class AvailableMoviesByDate(
        var date: LocalDate,
        var movies: List<EntityWithAmountOf<MovieResponse>>
)
