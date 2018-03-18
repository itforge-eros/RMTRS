package th.ac.kmitl.it.rmtrs.payload

data class EntityWithAmountOf<E>(
        var data: E,
        var amount_of: Map<String, Int>
)
