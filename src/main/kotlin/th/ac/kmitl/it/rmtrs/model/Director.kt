package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.SQLDelete
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@SQLDelete(sql = "UPDATE director SET is_active = false WHERE id = ?")
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

    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = [CascadeType.PERSIST, CascadeType.MERGE],
            mappedBy = "directors"
    )
    val movies: MutableSet<Movie> = HashSet()
}
