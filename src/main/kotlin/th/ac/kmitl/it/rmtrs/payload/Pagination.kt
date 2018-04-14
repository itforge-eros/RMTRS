package th.ac.kmitl.it.rmtrs.payload

import com.fasterxml.jackson.annotation.JsonProperty

data class Pagination(
        var total: Int,
        @JsonProperty("per_page")
        var perPage: Int,
        @JsonProperty("current_page")
        var currentPage: Int,
        @JsonProperty("last_page")
        var lastPage: Int,
        @JsonProperty("next_page_url")
        var nextPageUrl: String?,
        @JsonProperty("prev_page_url")
        var prevPageUrl: String?,
        var from: Int,
        var to: Int
)