package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.hibernate.annotations.Where
import th.ac.kmitl.it.rmtrs.util.LocalDateTimeToString
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Reservation(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        @get:JsonProperty("is_checked_in")
        var isCheckedIn: Boolean = false,

        @JsonProperty("checked_in_time")
        @JsonSerialize(using = LocalDateTimeToString::class)
        var checkedInTime: LocalDateTime? = null,

        @NotNull
        @JsonProperty("reserved_time")
        @JsonSerialize(using = LocalDateTimeToString::class)
        var reservedTime: LocalDateTime = LocalDateTime.now()
): BaseModel() {

    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var screening: Screening

    @OneToMany(
            mappedBy = "reservation",
            fetch = FetchType.EAGER,
            cascade = [CascadeType.ALL],
            orphanRemoval = false
    )
    @Where(clause = "is_active = true")
    val tickets: MutableSet<Ticket> = HashSet()
}