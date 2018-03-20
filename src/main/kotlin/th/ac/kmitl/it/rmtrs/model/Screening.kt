package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import th.ac.kmitl.it.rmtrs.util.LocalDateTimeToString
import th.ac.kmitl.it.rmtrs.util.LocalDateToString
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Screening(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        @JsonSerialize(using = LocalDateToString::class)
        var showDate: LocalDate = LocalDate.now(),

        @JsonSerialize(using = LocalDateTimeToString::class)
        var showTime: LocalDateTime = LocalDateTime.now()

): BaseModel() {

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @Where(clause = "is_active = true")
    lateinit var theatre: Theatre

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @Where(clause = "is_active = true")
    lateinit var movie: Movie

    @JsonIgnore
    @OneToMany(
            mappedBy = "screening",
            fetch = FetchType.LAZY,
            orphanRemoval = false
    )
    @Where(clause = "is_active = true")
    val reservations: MutableSet<Reservation> = HashSet()
}