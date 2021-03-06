package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import th.ac.kmitl.it.rmtrs.payload.ActorResponse
import javax.persistence.*
import javax.validation.constraints.NotNull

@SQLDelete(sql = "UPDATE actor SET is_active = false WHERE id = ?")
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

    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = [CascadeType.PERSIST, CascadeType.MERGE],
            mappedBy = "actors"
    )
    val movies: MutableSet<Movie> = HashSet()
}
