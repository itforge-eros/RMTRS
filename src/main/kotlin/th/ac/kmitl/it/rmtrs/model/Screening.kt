package th.ac.kmitl.it.rmtrs.model

import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Screening(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var startTime: LocalDateTime? = null

): BaseModel() {

    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var theatre: Theatre

    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var movie: Movie

    @OneToMany(
            mappedBy = "screening",
            fetch = FetchType.LAZY,
            orphanRemoval = false
    )
    val reservations: MutableSet<Reservation> = HashSet()
}