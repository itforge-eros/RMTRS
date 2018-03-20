package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import th.ac.kmitl.it.rmtrs.definition.Rate
import th.ac.kmitl.it.rmtrs.util.LocalDateToString
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Movie(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var th_title: String = "default",

        @NotNull
        var en_title: String = "default",

        @NotNull
        @Lob
        var synopsis: String = "default",

        @NotNull
        var duration: Int = 0,

        @NotNull
        @JsonProperty("poster_url")
        var posterUrl: String = "default",

        @NotNull
        @JsonProperty("trailer_url")
        var trailerUrl: String = "default",

        @NotNull
        @JsonSerialize(using = LocalDateToString::class)
        @JsonProperty("release_date")
        var releaseDate: LocalDate = LocalDate.now(),

        @NotNull
        @JsonSerialize(using = LocalDateToString::class)
        @JsonProperty("end_date")
        var endDate: LocalDate = LocalDate.now(),

        @NotNull
        var rate: Rate = Rate.G
): BaseModel() {

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_director",
            joinColumns = [JoinColumn(name = "movie_id")],
            inverseJoinColumns = [JoinColumn(name = "director_id")]
    )
    @Where(clause = "is_active = true")
    val directors: MutableSet<Director> = HashSet()

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_actor",
            joinColumns = [JoinColumn(name = "movie_id")],
            inverseJoinColumns = [JoinColumn(name = "actor_id")]
    )
    @Where(clause = "is_active = true")
    val actors: MutableSet<Actor> = HashSet()

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_genre",
            joinColumns = [JoinColumn(name = "movie_id")],
            inverseJoinColumns = [JoinColumn(name = "genre_id")]
    )
    @Where(clause = "is_active = true")
    val genres: MutableSet<Genre> = HashSet()

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_production",
            joinColumns = [JoinColumn(name = "movie_id")],
            inverseJoinColumns = [JoinColumn(name = "production_id")]
    )
    @Where(clause = "is_active = true")
    val productions: MutableSet<Production> = HashSet()

    @JsonIgnore
    @OneToMany(
            mappedBy = "movie",
            fetch = FetchType.EAGER,
            cascade = [CascadeType.MERGE, CascadeType.PERSIST],
            orphanRemoval = false
    )
    @Where(clause = "is_active = true")
    var screenings: MutableSet<Screening> = HashSet()
}
