package th.ac.kmitl.it.rmtrs.model

import th.ac.kmitl.it.rmtrs.payload.ActorResponse
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Actor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var fname: String = "default",

        @NotNull
        var mname: String = "default",

        @NotNull
        var lname: String = "default"
): BaseModel() {
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = [CascadeType.PERSIST, CascadeType.MERGE],
            mappedBy = "actors"
    )
    val movies: MutableSet<Movie> = HashSet()
}
