package th.ac.kmitl.it.rmtrs.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Director(
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
            mappedBy = "directors"
    )
    val movies: MutableSet<Movie> = HashSet()
}