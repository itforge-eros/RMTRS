package th.ac.kmitl.it.rmtrs.util

import org.springframework.data.domain.Page
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import th.ac.kmitl.it.rmtrs.payload.Links
import th.ac.kmitl.it.rmtrs.payload.PagedResponse
import th.ac.kmitl.it.rmtrs.payload.Pagination

fun <T> toPagedResponse(paged: Page<T>): PagedResponse<T> {

    val data = paged.content
    val nextPageUrl = when {
        paged.number == paged.totalPages - 1 -> null
        paged.number == 0 && paged.totalElements.toInt() == paged.numberOfElements -> null
        else -> ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .queryParam("page", paged.number+1)
                .toUriString()
    }
    val prevPageUrl = when(paged.number) {
        0 -> null
        else -> ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .queryParam("page", paged.number-1)
                .toUriString()
    }
    val from = if (paged.size * paged.number == 0) 1 else paged.size * paged.number
    val to = paged.size * paged.number + paged.numberOfElements
    val links = Links(Pagination(
            total = paged.totalElements.toInt(),
            currentPage = paged.number,
            perPage = paged.numberOfElements,
            lastPage = paged.totalPages - 1,
            from = from,
            to = to,
            nextPageUrl = nextPageUrl,
            prevPageUrl = prevPageUrl
    ))

    return PagedResponse<T>(links = links, data = data)
}