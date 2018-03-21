package th.ac.kmitl.it.rmtrs.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Ticket(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var itemNo: Int = 1
): BaseModel() {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @Where(clause = "is_active = true")
    @JsonIgnore
    lateinit var reservation: Reservation

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    @Where(clause = "is_active = true")
    lateinit var seat: Seat
}