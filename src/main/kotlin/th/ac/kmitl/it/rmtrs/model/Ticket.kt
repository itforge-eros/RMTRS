package th.ac.kmitl.it.rmtrs.model

import org.hibernate.annotations.SQLDelete
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
@SQLDelete(sql = "UPDATE ticket SET is_active = false WHERE id = ?")
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