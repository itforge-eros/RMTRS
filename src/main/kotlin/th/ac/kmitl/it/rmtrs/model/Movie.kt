package th.ac.kmitl.it.rmtrs.model

import th.ac.kmitl.it.rmtrs.definition.Rate
import java.time.LocalDateTime
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
        var releaseDate: LocalDateTime = LocalDateTime.now(),

        @NotNull
        var endDate: LocalDateTime = LocalDateTime.now(),

        @NotNull
        var rate: Rate = Rate.G
): BaseModel() {

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_director",
            joinColumns = [JoinColumn(name = "director_id")],
            inverseJoinColumns = [JoinColumn(name = "movie_id")]
    )
    val directors: MutableSet<Director> = HashSet()

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_actor",
            joinColumns = [JoinColumn(name = "actor_id")],
            inverseJoinColumns = [JoinColumn(name = "movie_id")]
    )
    val actors: MutableSet<Actor> = HashSet()

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_genre",
            joinColumns = [JoinColumn(name = "genre_id")],
            inverseJoinColumns = [JoinColumn(name = "movie_id")]
    )
    val genres: MutableSet<Genre> = HashSet()

    @ManyToMany(fetch = FetchType.EAGER, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinTable(
            name = "movie_production",
            joinColumns = [JoinColumn(name = "production_id")],
            inverseJoinColumns = [JoinColumn(name = "movie_id")]
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