package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Seat(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var row: String = "DEFAULT",

        @NotNull
        var number: Int = 0
): BaseModel() {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @Where(clause = "is_active = true")
    lateinit var seatType: SeatType

    @ManyToOne
    @JoinColumn(nullable = false)
    @Where(clause = "is_active = true")
    @JsonIgnore
    lateinit var  theatre: Theatre

    @OneToMany(
            mappedBy = "seat",
            fetch = FetchType.LAZY,
            cascade = [CascadeType.ALL],
            orphanRemoval = false
    )
    @Where(clause = "is_active = true")
    @JsonIgnore
    val tickets: MutableSet<Ticket> = HashSet()
}