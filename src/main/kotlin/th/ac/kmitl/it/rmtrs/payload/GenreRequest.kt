package th.ac.kmitl.it.rmtrs.payload

import javax.validation.constraints.NotBlank

class GenreRequest(
        var id: Long = 0,
        @field:NotBlank var name: String
)