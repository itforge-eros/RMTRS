package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.SQLDelete
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Genre(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var name: String = "default"
): BaseModel() {

    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = [CascadeType.PERSIST, CascadeType.MERGE],
            mappedBy = "genres"
    )
    val movies: MutableSet<Movie> = HashSet()
}
