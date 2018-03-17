package th.ac.kmitl.it.rmtrs.model

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

    @ManyToOne
    lateinit var seatType: SeatType

    @ManyToOne
    lateinit var  theatre: Theatre
}