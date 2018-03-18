package th.ac.kmitl.it.rmtrs.model

import th.ac.kmitl.it.rmtrs.definition.Rate
import th.ac.kmitl.it.rmtrs.payload.MovieResponse
import java.time.LocalDate
import java.time.LocalDateTime
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
        var posterUrl: String = "default",

        @NotNull
        var trailerUrl: String = "default",

        @NotNull
        var releaseDate: LocalDate = LocalDate.now(),

        @NotNull
        var endDate: LocalDate = LocalDate.now(),

        @NotNull
        var rate: Rate = Rate.G
): BaseModel() {

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_director",
            joinColumns = [JoinColumn(name = "movie_id")],
            inverseJoinColumns = [JoinColumn(name = "director_id")]
    )
    val directors: MutableSet<Director> = HashSet()

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_actor",
            joinColumns = [JoinColumn(name = "movie_id")],
            inverseJoinColumns = [JoinColumn(name = "actor_id")]
    )
    val actors: MutableSet<Actor> = HashSet()

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_genre",
            joinColumns = [JoinColumn(name = "movie_id")],
            inverseJoinColumns = [JoinColumn(name = "genre_id")]
    )
    val genres: MutableSet<Genre> = HashSet()

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_production",
            joinColumns = [JoinColumn(name = "movie_id")],
            inverseJoinColumns = [JoinColumn(name = "production_id")]
    )
    val productions: MutableSet<Production> = HashSet()

    @OneToMany(
            mappedBy = "movie",
            fetch = FetchType.EAGER,
            cascade = [CascadeType.MERGE, CascadeType.PERSIST],
            orphanRemoval = false
    )
    val screenings: MutableSet<Screening> = HashSet()
}

fun Movie.toResponse()
        = MovieResponse(
        id = this.id,
        th_title = this.th_title,
        en_title = this.en_title,
        synopsis = this.synopsis,
        duration = this.duration,
        posterUrl = this.posterUrl,
        trailerUrl = this.trailerUrl,
        releaseDate = this.releaseDate,
        endDate = this.endDate,
        rate = this.rate,
        actors = this.actors.map { it.toResponse() },
        genres = this.genres.map { it.toResponse() },
        productions = this.productions.map { it.toResponse() },
        directors = this.directors.map { it.toResponse() }
)
