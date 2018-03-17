package th.ac.kmitl.it.rmtrs.model

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

    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var reservation: Reservation

    @ManyToOne
    @JoinColumn(nullable = false)
    lateinit var seat: Seat
}