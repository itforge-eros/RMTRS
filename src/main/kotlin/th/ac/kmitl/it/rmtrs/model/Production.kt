package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.SQLDelete
import th.ac.kmitl.it.rmtrs.payload.ProductionResponse
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@SQLDelete(sql = "UPDATE production SET is_active = false WHERE id = ?")
data class Production(
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
            mappedBy = "productions"
    )
    val movies: MutableSet<Movie> = HashSet()
}
