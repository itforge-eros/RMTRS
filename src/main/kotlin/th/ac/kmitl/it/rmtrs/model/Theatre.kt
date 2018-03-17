package th.ac.kmitl.it.rmtrs.model

import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Theatre(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @NotNull
        var name: String = "default"

): BaseModel() {

    @OneToMany(
            mappedBy = "theatre",
            cascade = [CascadeType.ALL],
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    val seats: MutableSet<Seat> = HashSet()

    fun addSeat(seat: Seat) {
        seat.theatre = this
        seats.add(seat)
    }

    fun removeSeat(seat: Seat) {
        seats.remove(seat)
    }
}