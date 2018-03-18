package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.SQLDelete
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@SQLDelete(sql = "UPDATE screening SET is_active = false WHERE id = ?")
data class Screening(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var showDate: LocalDate = LocalDate.now(),

        var showTime: LocalDateTime = LocalDateTime.now()

): BaseModel() {

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var theatre: Theatre

    @JsonIgnore
    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var movie: Movie

    @JsonIgnore
    @OneToMany(
            mappedBy = "screening",
            fetch = FetchType.LAZY,
            orphanRemoval = false
    )
    val reservations: MutableSet<Reservation> = HashSet()
}