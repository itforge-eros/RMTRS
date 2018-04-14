package th.ac.kmitl.it.rmtrs.payload

data class PagedResponse<T> (
    var links: Links,
    var data: List<T> = emptyList()
)
