package th.ac.kmitl.it.rmtrs.model

import th.ac.kmitl.it.rmtrs.payload.ProductionResponse
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Production(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var name: String = "default"
): BaseModel() {

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = [CascadeType.PERSIST, CascadeType.MERGE],
            mappedBy = "productions"
    )
    val movies: MutableSet<Movie> = HashSet()
}
